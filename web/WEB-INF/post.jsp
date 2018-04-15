<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <link rel="stylesheet" href="/static/css/post-detail.css" >
    <script src="../static/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        function addOne() {
            var fdiv=document.getElementById("filediv");
            fdiv.innerHTML+='<div class="row mt-sm-3 mb-sm-3"><div class="col-sm-11"><input type="file" name="files" class="form-control-file" /></div><div class="col-sm-1"><input type="button" value="删除" class="btn" onclick="delOne(this)" id="delBut"></div></div>';
        }
        function delOne(obj){
            obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);
        }
    </script>
</head>
<body>
<%@ include file="header.jsp"%>
<div style="height:auto">
    <div class="container" style="margin-top: 50px" >
        <div class="row">
            <div class="col-sm-12">
                <!-- Breadcrumbs-->
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="mainforum.action">全部</a>
                    </li>
                    <li class="breadcrumb-item">
                        <a href="mainforum.action?mfid=<s:property value="post.subForum.mainForum.id"></s:property>"><s:property value="post.subForum.mainForum.name"></s:property></a>
                    </li>
                    <li class="breadcrumb-item active">
                        <a href="subforum.action?sfid=<s:property value="post.subForum.id"></s:property>"><s:property value="post.subForum.name"></s:property></a>
                    </li>
                </ol>
            </div>
        </div>

        <!-- 帖子内容 -->
        <div class="row">
            <div class="col-sm-2" style="padding-right: 0px;">
                <div class="post-head">
                    <div class="text-center">
                        <div>
                            <a href="#">
                                <img  alt="" class="img-responsive img-circle" src="/static/default.jpg"
                                      style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>
                            </a>
                        </div>
                        <div>
                            <span class="badge" style="background: #f1c40f;margin-top: 5px">发帖者:<s:property value="post.user.username"></s:property></span>
                        </div>
                        <div>
                            <span class="badge" style="background: #2ecc71;margin-top: 5px">性别:<s:property value="post.user.sex"></s:property></span>
                        </div>
                        <div>
                            <span class="badge" style="background: #ff6927;margin-top: 5px">论坛等级:LV<s:property value="post.user.level"></s:property></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-10" style="padding-left: 0px">
                <div class="post-content">
                    <div class="post-title">
                        <h2 style="margin-left:20px;color:black;"><s:property value="post.title"></s:property></h2>
                        <div style="margin-left:20px" >
                            <span class="glyphicon glyphicon-comment"></span>
                            回复数:<s:property value="post.followposts.size()"></s:property>
                            &nbsp;|&nbsp;
                            浏览数:<s:property value="post.viewNum"></s:property>
                            &nbsp;|&nbsp;
                            发表于:<s:date name="post.sendTime" format="yyyy-MM-dd HH:mm:ss"></s:date>
                            <strong style="float:right;margin-right:10px">
                                <span class="badge" style="background: #ff6927;width: 50px;">楼主</span>
                            </strong>
                            <s:if test="post.user.id==#session.user.id||#session.user.type==1">
                                <a style="float:right;margin-right: 20px;" href="deletePost.action?postid=<s:property value="post.id"></s:property>">删除</a>
                            </s:if>

                            <s:if test="post.user.id==#session.user.id">
                                <a style="float:right;margin-right: 20px;" href="updatePost?postid=<s:property value="post.id"></s:property>">编辑</a>
                            </s:if>
                            <a style="float:right;margin-right: 20px;" href="#">收藏</a>
                        </div>
                    </div>
                    <div style="margin: 20px">
                        ${post.content}
                        <s:iterator value="pictures" var="picture">
                            <img src="getPicture.action?id=<s:property value="#picture.id"></s:property>" width="200" height="150" alt="无法显示图片">
                        </s:iterator>
                    </div>
                </div>
            </div>
        </div>

        <s:iterator value="followposts" var="followpost">
            <!-- 回复内容 -->
            <div class="row" style="margin-top: 5px">
                <div class="col-sm-2" style="padding-right: 0px;">
                    <div class="reply-head">
                        <div class="text-center">
                            <div>
                                <a href="#">
                                    <img  alt="" class="img-responsive img-circle" src="/static/default.jpg"
                                          style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>
                                </a>
                            </div>
                            <div>
                                <span class="badge" style="background: #f1c40f;margin-top: 5px">回帖者:<s:property value="#followpost.user.username"></s:property></span>
                            </div>
                            <div>
                                <span class="badge" style="background: #2ecc71;margin-top: 5px">性别:<s:property value="#followpost.user.sex"></s:property></span>
                            </div>
                            <div>
                                <span class="badge" style="background: #ff6927;margin-top: 5px">论坛等级:LV<s:property value="#followpost.user.level"></s:property></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-10" style="padding-left: 0px;">
                    <div class="reply-content">
                        <div class="reply-time">
                            <span style="color: gray">回复于:<s:date name="#followpost.sendTime" format="yyyy-MM-dd HH:mm:ss"></s:date></span>
                            <div style="float:right;margin-right:10px">
                                <span class="badge" style="float:right;margin-right:10px;background: #4b9ded;width: 50px;">X楼</span>
                            </div>
                            <s:if test="#followpost.user.id==#session.user.id||#session.user.type==1">
                                <a style="float:right;margin-right: 20px;" href="deleteFollowpost.action?followpostid=<s:property value="#followpost.id"></s:property>">删除</a>

                            </s:if>
                            <s:if test="#followpost.user.id==#session.user.id">
                                <a style="float:right;margin-right: 20px;" href="updateFollowpost?followpostid=<s:property value="#followpost.id"></s:property>">编辑</a>
                            </s:if>
                        </div>
                        <div style="margin: 20px;">
                                ${followpost.content}
                                    <s:iterator value="#followpost.followpostPictures" var="picture">
                                        <img src="getPicture.action?id=<s:property value="#picture.id"></s:property>" width="200" height="150" alt="无法显示图片">
                                    </s:iterator>
                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>

        <div class="row">
            <div class="col-sm-12">
                <nav aria-label="Page navigation example" class="my-sm-3">
                    <ul class="pagination justify-content-center">
                        <s:if test='#request.pager.isFirstPage!=true'>
                            <li class="page-item">
                                <a class="page-link" href="post.action?postid=<s:property value="post.id"></s:property>&page=1">
                                    首页
                                </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link"
                                   href="post.action?postid=<s:property value="post.id"></s:property>&page=<s:property value="#request.pager.getCurrentPage-1"></s:property>" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                        </s:if>

                        <s:iterator value='#request.pager.getPageList' var="item">
                            <s:if test="#item==#request.pager.getCurrentPage">
                                <li class="page-item active">
                                    <a class="page-link" href=""><s:property value="#item"></s:property></a>
                                </li>
                            </s:if>
                            <s:elseif test="#item=='...'">
                                <li class="page-item">
                                    <div class="page-link">
                                        <s:property value="#item"></s:property>
                                    </div>
                                </li>
                            </s:elseif>
                            <s:else>
                                <li class="page-item">
                                    <a class="page-link"
                                       href="post.action?postid=<s:property value="post.id"></s:property>&page=<s:property value="#item"></s:property>">
                                        <s:property value="#item"></s:property>
                                    </a>
                                </li>
                            </s:else>
                        </s:iterator>
                        <s:if test='#request.pager.isLastPage!=true'>
                            <li class="page-item">
                                <a class="page-link"
                                   href="post.action?postid=<s:property value="post.id"></s:property>&page=<s:property value="#request.pager.getCurrentPage+1"></s:property>" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </s:if>
                    </ul>
                </nav>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <form action="commitFollowpost.action" method="post" enctype="multipart/form-data">
                    <div class="my-sm-2">
                        <textarea name="followpost.content" id="editor1" class="form-control"></textarea>
                        <script>
                            // Replace the <textarea id="editor1"> with a CKEditor
                            // instance, using default configuration.
                            CKEDITOR.replace( 'editor1' );
                        </script>
                    </div>
                    <div class="my-sm-2">
                        <input type="button" class="btn btn-primary" value="添加图片" onclick="addOne()" id="addBut">
                        <div id="filediv"></div>
                    </div>
                    <div class="my-sm-2" style="float:right;">
                        <input type="hidden" name="postid" value="<s:property value="post.id"></s:property>">
                        <input type="submit" class="btn btn-primary" value="发表">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>
<%@include file="foot.jsp"%>
</body>
</html>