<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-sm-5">
        <a class="navbar-brand" href="/">Parker<sup class="ml-sm-2">Beta</sup></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item">
                    <a class="nav-link" href="/mainforum.action">论坛</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/nbastream/index.action">NBA直播</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#">留言板</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/about.action">关于</a>
                </li>
            </ul>
            <ul class="navbar-nav navbar-right">
                <li class="nav-item mx-sm-5">
                    <form class="form-inline my-sm-0" action="/searchPosts.action" method="get">
                        <div class="input-group">
                            <input class="form-control" type="text" name="searchKeyWord" placeholder="搜索帖子">
                            <span class="input-group-append">
                                <input class="btn btn-primary" type="submit" value="搜索">
                                </input>
                            </span>
                        </div>
                    </form>
                </li>

                <s:if test="#session.user.getUsername()!=null">
                    <li class="nav-item">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">欢迎您，${sessionScope.user.getUsername()}</a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/setting.action">个人中心</a>
                                <a class="dropdown-item" href="/logout.action"><span class="fa fa-fw fa-sign-out"></span>退出</a>
                                <s:if test="#session.user.getType()>=1">
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="/manage/index.action">后台管理</a>
                                </s:if>
                            </div>
                        </li>
                    </li>
                </s:if>
                <s:else>
                    <li class="nav-item">
                        <a class="nav-link" href="/login.action"><span class="fa fa-fw fa-sign-in"></span>登录</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/register.action">注册</a>
                    </li>
                </s:else>
            </ul>
        </div>
    </nav>
</header>