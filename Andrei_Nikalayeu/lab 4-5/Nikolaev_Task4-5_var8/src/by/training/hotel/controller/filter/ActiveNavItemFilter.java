package by.training.hotel.controller.filter;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ActiveNavItemFilter implements Filter {
    static Set<String> navItems = null;

    static {
        navItems = new HashSet<String>();

        navItems.add("/user/list");
        navItems.add("/room/list");
        navItems.add("/order/list");
        navItems.add("/order/edit");
        navItems.add("/info");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws java.io.IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");

        if (postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }

        httpReq.setAttribute("userListItem", (url.equals("/user/list")) ? true : false);
        httpReq.setAttribute("roomListItem", (url.equals("/room/list")) ? true : false);
        httpReq.setAttribute("orderListItem", (url.equals("/order/list")) ? true : false);
        httpReq.setAttribute("orderEditItem", (url.equals("/order/edit")) ? true : false);
        httpReq.setAttribute("login", (url.equals("/login")) ? true : false);
        httpReq.setAttribute("info", (url.equals("/info")) ? true : false);

        chain.doFilter(req, resp);
    }

}