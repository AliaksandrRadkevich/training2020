package hospitalProject.by.epam.web;

import java.io.IOException;
import java.util.Arrays;
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

import hospitalProject.by.epam.domain.Role;
import hospitalProject.by.epam.domain.User;

public class SecurityFilter implements Filter{
    private static final Map<String, Set<Role>> accessList = new HashMap<>();
    
    static {
        Set<Role> all = new HashSet<>();
        all.addAll(Arrays.asList(Role.values()));
        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMIN);
        Set<Role> physicianAndNurse = new HashSet<>();
        physicianAndNurse.add(Role.PHYSICIAN);
        physicianAndNurse.add(Role.NURSE);
        Set<Role> physicianAndPatient = new HashSet<>();
        physicianAndPatient.add(Role.PHYSICIAN);
        physicianAndPatient.add(Role.PATIENT);
        Set<Role> physician = new HashSet<>();
        physician.add(Role.PHYSICIAN);
        
        accessList.put("/user/list", admin);
        accessList.put("/user/edit", admin);
        accessList.put("/user/save", admin);
        accessList.put("/user/delete", admin);
        
        accessList.put("/patient/find", physicianAndNurse);
        accessList.put("/patient/get", physicianAndNurse);
        accessList.put("/patient/list", physicianAndNurse);
        accessList.put("/prescription/edit", physicianAndNurse);
        accessList.put("/prescription/save", physicianAndNurse);
        
        accessList.put("/prescription/list", physicianAndPatient);
        accessList.put("/record/list", physicianAndPatient);
        
        accessList.put("/prescription/delete", physician);
        accessList.put("/record/edit", physician);
        accessList.put("/record/delete", physician);
        accessList.put("/record/save", physician);
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)arg0;
        HttpServletResponse httpResp = (HttpServletResponse)arg1;
        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = url.indexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        }
        else {
            url = url.substring(context.length());
        }
        
        Set<Role> roles = accessList.get(url);
        if(roles != null) {
            HttpSession session = httpReq.getSession(false);
            if(session != null) {
                User user = (User)session.getAttribute("session_user");
                if(user != null && roles.contains(user.getRole())) {
                    arg2.doFilter(arg0, arg1);
                    return;
                }
            }
        }
        else {
            arg2.doFilter(arg0, arg1);
            return;
        }
        httpResp.sendRedirect(httpReq.getContextPath() + "/login.html?message=Access denied");
    }
}
