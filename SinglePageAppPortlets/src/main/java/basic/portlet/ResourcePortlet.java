/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package basic.portlet;

import static basic.portlet.Constants.PARAM_BORDER_COLOR;
import static basic.portlet.Constants.PARAM_COLOR;
import static basic.portlet.Constants.PARAM_IMGNAME;
import static basic.portlet.Constants.imgMap;
import static basic.portlet.Constants.ATTR_TITLE;
import static basic.portlet.Constants.DEFAULT_IMAGE;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * A management portlet that displays the current deep link configuraion
 */
public class ResourcePortlet extends GenericPortlet {

   // Set up logging
   private static final String LOG_CLASS = ResourcePortlet.class.getName();
   private final Logger logger = Logger.getLogger(LOG_CLASS);


   protected void doView(RenderRequest req, RenderResponse resp)
         throws PortletException, IOException {
      
      if (logger.isLoggable(Level.FINE)) {
         logger.logp(Level.FINE, this.getClass().getName(), "doView", "Entry");
      }
      
      resp.setContentType("text/html");
      req.setAttribute(ATTR_TITLE, getTitle(req));

      PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(
         "/WEB-INF/jsp/view-ivp.jsp");
      rd.include(req, resp);
      

   }
   
   /* (non-Javadoc)
    * @see javax.portlet.GenericPortlet#serveResource(javax.portlet.ResourceRequest, javax.portlet.ResourceResponse)
    */
   @Override
   public void serveResource(ResourceRequest req, ResourceResponse resp)
         throws PortletException, IOException {

      String key = req.getParameter(PARAM_IMGNAME);
      String imgDir = DEFAULT_IMAGE;
      if ((key != null) && imgMap.containsKey(key)) {
         imgDir = imgMap.get(key);
      }
      
      String bc = req.getParameter(PARAM_BORDER_COLOR);
      String imgStyle = "";
      if (bc != null) {
         imgStyle = " style='border:1px solid " + bc + ";' ";
      }
      
      String ctx = req.getContextPath();
      String ca = req.getCacheability();
      
      resp.setContentType("text/html");
      PrintWriter writer = resp.getWriter();
      
      String clr = req.getParameter(PARAM_COLOR);
      clr = (clr == null) ? "#FFFFFF" : clr;

      // add action button if cacheability allows -
      PortletURL aurl = null;
      String bmu = "<p>Action URL could not be created.</p>";
      try {
         aurl = resp.createActionURL();
      } catch (Exception e) {}
      if (aurl != null) {
         bmu = "<form  onsubmit='return false;' METHOD='POST' ACTION='" + aurl + "'><input id='<portlet:namespace/>-clear' type='submit' name='action' value='Action' /></form>";
      }

      writer.write("<div style='background-color:" + clr + ";'>\n");
      writer.write("   <table style='background-color:" + clr + ";'>");
      writer.write("   <tr><td align='center' style='background-color:" + clr + ";'>");
      writer.write("   <img src='" + ctx + imgDir + "'" + imgStyle + ">\n");
      writer.write("   </td><td style='background-color:" + clr + ";'>");
      writer.write("   <p>" + bmu + "</p>");
      writer.write("   <p>Cacheability: " + ca + "</p>");
      writer.write("   </td></tr>");
      writer.write("   </table>");
      writer.write("</div>\n");


   }

   public void processAction(ActionRequest req, ActionResponse resp)
         throws PortletException, IOException {
   }

}
