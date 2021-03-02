package by.training.hotel.controller.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.hotel.model.di.ServiceCreator;
import by.training.hotel.model.di.impl.ServiceCreatorImpl;
import by.training.hotel.model.domain.user.Role;
import by.training.hotel.model.domain.user.User;
import by.training.hotel.model.service.RoleService;

public class SecurityFilter implements Filter {
    private static final Map<String, Set<Role>> permissions = new HashMap<>(); // Разрешения
    static Set<Role> all = null;
    static Set<Role> admin = null;
    static Set<Role> client = null;

    static {
        try (ServiceCreator serviceCreator = new ServiceCreatorImpl()) {
            RoleService roleService = serviceCreator.getRoleService();

            all = new HashSet<>();
            all.addAll(roleService.findAll());

            admin = new HashSet<>();
            admin.add(roleService.findById(1L));

            client = new HashSet<>();
            client.add(roleService.findById(3L));
        } catch (Exception e) {}

        permissions.put("/user/list", admin);
        permissions.put("/user/edit", admin);
        permissions.put("/user/save", admin);
        permissions.put("/user/delete", admin);
        permissions.put("/user/delete_list", admin);

        permissions.put("/room/list", admin);
        permissions.put("/room/edit", admin);
        permissions.put("/room/save", admin);
        permissions.put("/room/delete", admin);
        permissions.put("/room/delete_list", admin);

        permissions.put("/order/list", all);
        permissions.put("/order/edit", all);
        permissions.put("/order/save", all);
        permissions.put("/order/delete", all);
        permissions.put("/order/delete_list", all);

        permissions.put("/info", all);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;

        String url = httpReq.getRequestURI();

        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");

        if (postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }

        Set<Role> roles = permissions.get(url);

        if (roles != null) {
            HttpSession session = httpReq.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("session_user");
                if (user != null && roles.contains(user.getRole())) {
                    httpReq.setAttribute("adminDataAccess", (admin.contains(user.getRole())) ? true : false);
                    chain.doFilter(req, resp);
                    return;
                } else {
                    httpResp.sendError(403);
                }

            }
        } else {
            chain.doFilter(req, resp);
            return;
        }
        httpResp.sendRedirect(context + "/login.html?message=login.message.access.denied");
    }
}
