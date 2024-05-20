package com.webtutorial.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.webtutorial.dao.GenericDAO;
import com.webtutorial.mapper.IRowMapper;

public abstract class AbstractDAO<T> implements GenericDAO<T> {
		private static ResourceBundle  resourceBundle = ResourceBundle.getBundle("db");	
	@Override
	//Dùng chung vs delete
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					System.out.println("Đã rollback");
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection = null; 
		PreparedStatement preparableStatement = null;
		ResultSet resultSet = null;
		Long id = null;
		try {
			 connection =  AbstractDAO.getConnection();
			 connection.setAutoCommit(false);
			 preparableStatement =connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//sinh ra id	 
			//preparableStatement.setInt(1, 1);
			setParameter(preparableStatement, parameters);
			 preparableStatement.executeUpdate();
			 resultSet = preparableStatement.getGeneratedKeys();//Lấy id đc sinh ra từ câu sql
			 if(resultSet.next()) {
				 id = resultSet.getLong(1);
			 }
			connection.commit();//Nếu connection == null thì ko commit
			//trong try có 5 dòng code nếu chạy đến dòng thứ 3 mà gặp lỗi nó nhảy sang khối catch chứ không chạy nốt dòng code 4, 5
			return id;
		} catch (SQLException e) {
			if(connection != null) {
				try {
					System.out.println("rollback");
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		}finally {
			try {
				if(connection != null) {
					AbstractDAO.closeConnection(connection);
				}
				if(preparableStatement != null) {
					preparableStatement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e2) {
				return null;
			}
		}
		return id;
	}
	@Override
	
	public ArrayList<T> queryList(String sql, IRowMapper<T> rowMapper, Object... parameters) {
		Connection connection = null; 
		PreparedStatement preparableStatement = null;
		ResultSet resultSet = null;
		ArrayList<T> arrayList = new ArrayList<>();
		try {
			 connection =  AbstractDAO.getConnection();
			 preparableStatement =connection.prepareStatement(sql);	 
			//preparableStatement.setInt(1, 1);
			setParameter(preparableStatement, parameters);
			 resultSet = preparableStatement.executeQuery();
			while (resultSet.next()) {
				//rowMapper sẽ nhận tham số là : new CategoryMapper là 1 object nên nó gọi đc method mapRow trong CategoryMapper
				arrayList.add(rowMapper.mapRow(resultSet));
			}
		} catch (SQLException e) {
			return null;
		}finally {
			try {
				if(AbstractDAO.getConnection() != null) {
					AbstractDAO.closeConnection(connection);
				}
				if(preparableStatement != null) {
					preparableStatement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e2) {
				
			}
		}
		return arrayList;
	}
		public void setParameter(PreparedStatement preparedStatement, Object...parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				int index = i + 1;
				Object parameter = parameters[i];
				if(parameter instanceof Long) {
					preparedStatement.setLong(index, (Long)parameter);
				} else if (parameter instanceof String) {
					preparedStatement.setString(index, (String) parameter);
				} else if (parameter instanceof Integer) {
					preparedStatement.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					preparedStatement.setTimestamp(index, (Timestamp) parameter);
				}else if(parameter == null) {
					preparedStatement.setNull(index, Types.NULL);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//Phần connect vs database này nên tạo thành hardcode ở 1 file db.properties
	public static Connection getConnection() {	
		try {
//			Class.forName("com.mysql.jdbc.Driver");//Tạo trình điều khiển để có thể kết nối đc với Mysql
//			Connection connection = null;
//			String url = "jdbc:mysql://localhost:3306/newservlet";
//			String user = "root";
//			String password = "21102004";
			Class.forName(resourceBundle.getString("driverName"));//Gọi các key của file db.properties
			Connection connection = null;
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			
			return null;
		}
	}
	public static void closeConnection(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
}
