<%--
  Auth : nicekkong@gmail.com
  Date : 2016. 9. 18. 오후 9:29
  Desc : 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${userInfo.name} 님</p>
                <!-- Status -->
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>

        <!-- search form (Optional) -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu">
            <li class="header">메뉴를 골라주세요</li>
            <!-- Optionally, you can add icons to the links -->
            <li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>
            <li id="freeBoard"><a href="/board/freeBoard"><i class="fa fa-link"></i> <span>자유 게시판</span></a></li>
            <li class="treeview">
                <a href="#"><i class="fa fa-link"></i> <span>Multilevel</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="#">Link in level 2</a></li>
                    <li><a href="#">Link in level 2</a></li>
                </ul>
            </li>
            <li><a href="/index2.html"><i class="fa fa-link"></i> <span>Sample Template</span></a></li>

        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>
<script>

    var pathName = location.pathname;
    //console.log("class Name : " , jQuery('.sidebar-menu').attr('id'));
    var activeSelectMenu= (function (){
        //console.log("side_menu~!!!!!!!!!", pathName);

        var menu_list = jQuery('.sidebar-menu');
        //console.log(menu_list);
        //console.log(menu_list.find('li').length);
        menu_list.find('li').each(function(index, element){
            //console.log(index, ':' , jQuery(element).attr('id'));
            //console.log("this", element.id, "pathname", pathName);
            jQuery(this).removeClass('active');
            if(pathName.indexOf(element.id) > 0) {
                //console.log("here~!!!", pathName.indexOf(element.id));
                jQuery(this).addClass('active');
            }
        });
    })();

</script>