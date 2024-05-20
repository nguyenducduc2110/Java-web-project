<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-news"/> <!--Khai báo biến url có value = "1URL" để cho hàm ajax sử dụng -->
<c:url var ="NewsURL" value="/admin-news"/>
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa bài viết</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                        <!-- Thông báo khi add, delete done one a bài viết -->
                        <c:if test="${not empty requestScope.messageResponse}"><!-- Lấy value từ request đc set Attribute -->
							<div class="alert alert-${requestScope.alert}">
  								<strong>${requestScope.alert} </strong>${requestScope.messageResponse} 
							</div>
						</c:if>
                        <form id="formSubmit">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="categoryId" name="categoryId">
                                    <!-- NẾu là thêm mới -->
                                        <c:if test="${empty newsModel}">
                                            <option value="">Chọn loại bài viết</option>
                                            <c:forEach var="item" items="${categories}">
                                            <!-- tức là set value cho thẻ option thì ta có thể dùng biến của js để lấy value trong option -->
                                            	<!--value="${item.id}" là khi chọn category Thể thao thì value này sẽ set id cho value của thẻ <select> ở trên và ánh xạ sang servlet-->
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                               
                                            
                                        </c:if>
                                        <!-- Nếu là update -->
                                        <c:if test="${not empty newsModel}">
                                            <option value="">Chọn loại bài viết</option>
                                            <c:forEach var="item" items="${categories}">
                                            								<!--Đoạn code trên là khi đkiện đúng thì lựa chọn của người dùng lúc trước sẽ đc set ở option(hayđc hiểu là set thuộc tính selected cho option  -->
                                            								<!-- Nếu id của category hiện tại = categoryId của newsModel lấy catogoryName mà id đại diện cho -->
                                                <option value="${item.id}" <c:if test="${item.id == newsModel.categoryId}">selected="selected"</c:if>>
                                                        ${item.name} 
                                                </option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                                <div class="col-sm-9">
                                	<!-- INput để là text thì value sẽ hiện chữ lên  -->
                                	
                                    <input type="text" class="form-control" id="title" name="title" value="${newsModel.title}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="thumbnail" name="thumbnail" value="${newsModel.thumbnail}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="shortDescription" name="shortDescription" value="${newsModel.shortDescription}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                                <div class="col-sm-9">                                 
                                    <textarea rows="" cols="" id="content" name="content" style="width: 750px;height: 175px">${newsModel.content}</textarea>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <div class="col-sm-12">
                                <!-- Ktra nếu có newsModel thì nút có chữ Cập nhật bài viết và ngược lại-->
                                    <c:if test="${not empty requestScope.newsModel}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật bài viết" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                    <c:if test="${empty requestScope.newsModel}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm bài viết" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                </div>
                            </div>
                            <!-- Dùng để chứa id của newsModel set sang và thẻ input này dùng để lấy biến ở thẻ input cho hàm JS ở dưới dùng -->
                            <input type="hidden" value="${newsModel.id}" id="id" name="id"/>
                        </form>
                        <!-- All thằng trong form này sẽ đc submit nhưng nó khác formSubmit thuần ở phân trang -->
                        <!--vì thẻ input này vừa hiển thị và nhập vào đc rồi gửi xuống server.Còn submit ở phân trang chỉ gửi value xuống server -->
                </div>
            </div>
        </div>
    </div>
</div>
<script>
//textArea là ckeditor simple còn dưới đây là biến textArea nó simple thành có nhiều tool hỗ trợ viết text
var editor = '';
$(document).ready(function(){
	editor = CKEDITOR.replace( 'content');//tạo 
});

//1
$('#btnAddOrUpdateNew').click(function (e) {//Khi click vào button cập nhật or thêm news
    e.preventDefault();//Cái dùng để nó submit vào url = /api-admin-news
    var data = {};//mảng để chứa các object js
    var formData = $('#formSubmit').serializeArray();//serializeArray dùng để thuần tự hoá các của input trong formSubmit thành 1 mảng các đối tượng.Mỗi object là có dạng { name: "title", value: "newsModel.title" }
  //vòng for of jquery cho chuỗi formData vào mảng js object(mảng giống hashmap có lấy value dựa vào key)
    $.each(formData, function (i, v) {//v ở đây là cặp key-value của object js: v.name là lấy name, v.value là lấy value của object js
        data[""+v.name+""] = v.value;//Lấy từng cặp name-value, như kiểu mảng data tại vị trí name = value (data[name] = value)
    });
//2: 1->2: là lấy giá trị trong có ô nhập liệu cho vào 1 mảng rồi truyền cho servlet thông qua AJAX
    //data["content"] = editor.getData();
    var id = $('#id').val();//Hàm val này vừa dùng để get và set value.Lấy id từ newsModel lên để edit 
    if (id == "") {//null thì là thêm mới.
        addNew(data);//Đẩy data đến servlet vào methdo Post có url = /api-admin-news để insert data xuống server 
    } else {//là chỉnh sửa
        updateNew(data);//Đẩy data đến servlet vào methdo Put có url = /api-admin-news để update data dưới server 
    }
});
//Đây là method của js dùng để nhận 1 mảng data trên thanh input rồi gửi sang servlet bằng kiểu data JSON
function addNew(data) {//hàm js
    $.ajax({//hàm ajax
        url: '${APIurl}',//biến url có value là 1 url muốn đc ajax gửi request vào
        type: 'POST',
        contentType: 'application/json',//cặp giá trị trong mảng đc gửi dạng JSON
        data: JSON.stringify(data),//Dùng để chuyển các JavaScript Object thành JSON
        dataType: 'json',//Thông báo cho JSP là servlet trả về JSON
        //Sau khi post data lên thì servlet trả về result(resutl đc trả về dạng JSON)
       	//Servlet trả về do dev setup và bài viết trả lên để hiện thị bài viết vừa thêm
        success: function (result) {//result này là data vừa đẩy sang server(result này là 1 newsModel) 
        	//Đây thực chất là đang bảo server sendDirect sang 1 jsp khác cùng param của nó
        	window.location.href =  "${NewsURL}?type=edit&id="+result.id+"&message=insert_success";
        },
        error: function (error) {
        	window.location.href = "${NewsURL}?type=list&maxPageItem=2&page=1&message=error_system";
        }
    });
}
function updateNew(data) {
    $.ajax({
        url: '${APIurl}',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',//Thông báo cho JSP là servlet trả về JSON
////result này là 1 newsModel vừa update (Lý do result lấy đc newsModel là ở /api-admin-news 
		//sau khi update or insert thì /api-admin-news trả về luồng có 1 object vừa insert: objectMapper.writeValue(resp.getOutputStream(), newsModel);)
        success: function (result) {
        	window.location.href ="${NewsURL}?type=edit&id="+result.id+"&message=insert_success";
        },
        error: function (error) {
        	window.location.href = "${NewsURL}?type=list&maxPageItem=2&page=1&message=error_system";
        }
    });
}
	
</script>
</body>
</html>