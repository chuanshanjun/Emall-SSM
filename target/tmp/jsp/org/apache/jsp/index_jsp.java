/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.6.v20170531
 * Generated at: 2017-09-20 09:58:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("file:/D:/JAVAPrograms/MavenJar/repository/javax/servlet/jstl/1.2/jstl-1.2.jar", Long.valueOf(1490065169270L));
    _jspx_dependants.put("jar:file:/D:/JAVAPrograms/MavenJar/repository/javax/servlet/jstl/1.2/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153356282000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html\"; charset=\"utf-8\" >\r\n");
      out.write("    <title>京西(JX.COM)-综合网购首选-正品低价、品质保障、配送及时、轻松购物！</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/css/reset.css\" type=\"text/css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/css/style.css\" type=\"text/css\">\r\n");
      out.write("</head>\r\n");
      out.write("<div id=\"head\">\r\n");
      out.write("    <div class=\"head_top\">\r\n");
      out.write("        <div class=\"wrap clearfix\">\r\n");
      out.write("            <div class=\"leftArea\">\r\n");
      out.write("                <a href=\"#\" id=\"collection\">收藏京西</a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"rightArea\">欢迎来到京西网！<a href=\"/malluser/toLogin.do\">[登录]</a><a href=\"/malluser/toRegister.do\">[免费注册]</a></div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"search\">\r\n");
      out.write("        <div class=\"wrap\">\r\n");
      out.write("            <div class=\"logo\">\r\n");
      out.write("                <a href=\"#\"><img src=\"/images/logo.png\" alt=\"京西商城\"></a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"search_box\">\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"shop_car\">\r\n");
      out.write("                <span class=\"car\"><a href=\"/shopCart/toCart.do\"/>购物车</span>\r\n");
      out.write("                <span class=\"num_text\">0</span>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"nav\">\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"banner\">\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"goodshow\">\r\n");
      out.write("    <div class=\"shopArea\" v-for=\"xx in category\"><!--遍历商品类目-->\r\n");
      out.write("        <div class=\"wrap\">\r\n");
      out.write("            <div class=\"shop_title\">\r\n");
      out.write("                <h3>{{xx.typeName}}</h3>\r\n");
      out.write("                <span>更多&gt;&gt;</span>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"main clearfix\">\r\n");
      out.write("                <div class=\"shop_banner\">\r\n");
      out.write("                    <img src=\"/images/ad01.jpg\" alt=\"shop_banner\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"shop_list\">\r\n");
      out.write("                    <div class=\"shoplist_box clearfix\">\r\n");
      out.write("                        <div class=\"shopItem_top\" v-for=\"(good, index) in xx.goods\" v-if=\"index < 4\"><!--遍历商品详情-->\r\n");
      out.write("                            <div class=\"shop_img\">\r\n");
      out.write("                            <a v-bind:href=\"'/good/toGoodsIntroduce.do?goodId=' + good.id\">\r\n");
      out.write("                                <img src=\"/images/HTC.jpg\" alt=\"商品\">\r\n");
      out.write("                            </a>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <h4>{{good.name}}</h4>\r\n");
      out.write("                            <p>{{good.price}}元</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"shopItem_bottom\" v-for=\"(good, index) in xx.goods\" v-if=\"index >= 4\">\r\n");
      out.write("                            <div class=\"shop_img\">\r\n");
      out.write("                                <a v-bind:href=\"'good/toGoodsIntroduce.do?goodId=' + good.id\">\r\n");
      out.write("                                    <img src=\"/images/NFC.jpg\" alt=\"商品\">\r\n");
      out.write("                                </a>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"shop_text\">\r\n");
      out.write("                                <p>{{good.name}}</p>\r\n");
      out.write("                                <span>￥{{good.price}}</span>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"footer\">\r\n");
      out.write("    <p>慕课简介|慕课公告| 招纳贤士| 联系我们|客服热线：400-675-1234</p>\r\n");
      out.write("    <p>Copyright © 2006 - 2014 慕课版权所有   京ICP备09037834号   京ICP证B1034-8373号   某市公安局XX分局备案编号：123456789123</p>\r\n");
      out.write("    <div class=\"credit_rating\">\r\n");
      out.write("        <a href=\"#\"><img src=\"/images/pj.jpg\" alt=\"信用评价\"></a>\r\n");
      out.write("        <a href=\"#\"><img src=\"/images/pj.jpg\" alt=\"信用评价\"></a>\r\n");
      out.write("        <a href=\"#\"><img src=\"/images/pj.jpg\" alt=\"信用评价\"></a>\r\n");
      out.write("        <a href=\"#\"><img src=\"/images/pj.jpg\" alt=\"信用评价\"></a>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-mini.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/vue-mini.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    //将需要加载的资源放在下面，先给客户展示些页面信息\r\n");
      out.write("    var goodshow = {};\r\n");
      out.write("        $(document).ready(function () {\r\n");
      out.write("            goodshow = new Vue({\r\n");
      out.write("                el : '#goodshow',\r\n");
      out.write("                data : {\r\n");
      out.write("                    category : [\r\n");
      out.write("                    ]\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("\r\n");
      out.write("            listSort();//首先遍历商品类目的信息\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("    function listSort() {\r\n");
      out.write("        $.ajax({\r\n");
      out.write("            url:'/show/type.do',\r\n");
      out.write("            type:'POST', //GET\r\n");
      out.write("            async:true,    //或false,是否异步\r\n");
      out.write("            data:{\r\n");
      out.write("            },\r\n");
      out.write("            timeout:5000,    //超时时间\r\n");
      out.write("            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text\r\n");
      out.write("            beforeSend:function(xhr){\r\n");
      out.write("                console.log(xhr)\r\n");
      out.write("                console.log('发送前')\r\n");
      out.write("            },\r\n");
      out.write("            success:function(data,textStatus,jqXHR){\r\n");
      out.write("\r\n");
      out.write("                if (!data.success) {\r\n");
      out.write("                    alert(data.message);\r\n");
      out.write("                    return;\r\n");
      out.write("                }\r\n");
      out.write("\r\n");
      out.write("                goodshow.category = data.data;//将遍历出来的商品类目放到数组中\r\n");
      out.write("                var len = data.data.length;\r\n");
      out.write("                for (var i=0; i<len; i++) {\r\n");
      out.write("                    var cg = goodshow.category[i];\r\n");
      out.write("                    listGoods(cg, i);//遍历每个商品类目中的商品详情\r\n");
      out.write("                }\r\n");
      out.write("\r\n");
      out.write("            },\r\n");
      out.write("            error:function(xhr,textStatus){\r\n");
      out.write("                console.log('错误')\r\n");
      out.write("                console.log(xhr)\r\n");
      out.write("                console.log(textStatus)\r\n");
      out.write("            },\r\n");
      out.write("            complete:function(){\r\n");
      out.write("                console.log('结束')\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function listGoods(cg, index) {\r\n");
      out.write("        $.ajax({\r\n");
      out.write("            url:'/show/goods.do',\r\n");
      out.write("            type:'POST', //GET\r\n");
      out.write("            async:true,    //或false,是否异步\r\n");
      out.write("            data:{\r\n");
      out.write("                typeId: cg.id,\r\n");
      out.write("            },\r\n");
      out.write("            timeout:5000,    //超时时间\r\n");
      out.write("            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text\r\n");
      out.write("            beforeSend:function(xhr){\r\n");
      out.write("                console.log(xhr)\r\n");
      out.write("                console.log('发送前')\r\n");
      out.write("            },\r\n");
      out.write("            success:function(data,textStatus,jqXHR){\r\n");
      out.write("\r\n");
      out.write("                if (!data.success) {\r\n");
      out.write("                    alert(data.message);\r\n");
      out.write("                    return;\r\n");
      out.write("                }\r\n");
      out.write("\r\n");
      out.write("                cg[\"goods\"] = data.data;//将遍历出来的商品信息放到数组中\r\n");
      out.write("                goodshow.category.splice(index, 1, cg)//将带有商品详情的数组插入category中\r\n");
      out.write("\r\n");
      out.write("            },\r\n");
      out.write("            error:function(xhr,textStatus){\r\n");
      out.write("                console.log('错误')\r\n");
      out.write("                console.log(xhr)\r\n");
      out.write("                console.log(textStatus)\r\n");
      out.write("            },\r\n");
      out.write("            complete:function(){\r\n");
      out.write("                console.log('结束')\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}