/*
 * Copyright 2012 Kantega AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kwashc.blog.servlet;

import kwashc.blog.database.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check if we should delete a comment:
        String commentToDelete = request.getParameter("commentToDelete");
        if (commentToDelete != null) {
            Database.deleteComment(Integer.parseInt(commentToDelete));
        }

        /* no no, not allowed to touch this */
        String checkIfMyPasswordStoredSecurely = request.getParameter("checkIfMyPasswordStoredSecurely");
        if(checkIfMyPasswordStoredSecurely != null){
            response.setContentType("text(html");
            PrintWriter writer = null;
            try{
                StringBuffer res = new StringBuffer("<html><head></head><body>");
                res.append("P1:" + Database.getUser("username").getPassword() + ":P1 P2:" + Database.getUser("guest").getPassword() + ":P2");
                res.append("</body></html>");
                writer = response.getWriter();
                writer.print(res);
                writer.flush();
            } catch (Exception e ){
                //    
            } finally {
                if(writer != null) writer.close();
            }
        } else {
            response.sendRedirect("/blog");
        }
        /* no no, not allowed to touch this */
    }
}
