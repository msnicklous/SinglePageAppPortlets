# SinglePageAppPortlets

This repository contains portlets that use the IBM Single-Page Application Theme
Module for WebSphere Portal to update private and public render parameters and
execute portlet actions without causing a page refresh.

These portlets, along with the IBM Single-Page Application Theme Module, provides
a WebSphere Portal-based technical preview of the upcoming JSR 362 Portlet Specification 3.0
portlet hub functionality.

In order to avoid clashes with the final JSR 362 namespace, the JavaScript functionality 
provided by the IBM Single-Page Application Theme Module uses the IBM-specific namespace 'wpModules.portlethub'.

Note that the JSR 362 specification is not final, so the API used by these portlets is subject to change.

###Prerequisites

The portlets require WebSphere Portal version 8.5 with the IBM Single-Page
Application Theme Module for WebSphere Portal installed. The theme module is
available from the
[IBM Solutions Catalog](https://greenhouse.lotus.com/plugins/plugincatalog.nsf/home_full.xsp) site.

###Installation

0. Make sure the IBM Single-Page Application Theme Module for WebSphere Portal is installed properly.
1. Clone this repository. 
2. On your local machine switch to the 'SinglePageAppPortlets' directory under the repository location.
3. Build the project using the maven command 'mvn install'.
4. Deploy the SinglePageAppPortlets.war file on your IBM WebSphere Portal server using WebSphere Portal tools.
5. Create a portal page for the single-page application. Two-column layout is recommended.
6. Place the 'PH Image Selection Portlet' and the 'PH Color Selection Portlet' portlets in the left column.
7. Place the 'PH Resource Portlet' and the 'PH Message Box Portlet' portlets in the right column.
8. Create a wire from the 'PH Color Selection Portlet' to the 'PH Message Box Portlet'.

Your completed portal page should look similar to this:

![Single-Page App Page](/portalpage.gif)

###Usage

These portlets update private and public render parameters, execute portlet actions,
and create resource URLs containing the current page state without causing a full page refresh.
They use standard JavaScript with no special library support other than the
portlet hub API provided by the IBM Single-Page Application Theme Module to accomplish this.

The portlet UI controls cause private and public render parameter changes, and initiate 
portlet actions. 
When a portlet action is executed, portlet code running on WebSphere Portal causes a 
server-side event to be fired, which results in an update to a different portlet.
Use the portlet UI to make changes while noting that only the affected portions of the
portal page, as opposed to the entire page, are updated.

####The PH Image Selection Portlet

The PH Image Selection Portlet uses a private render parameter to control the selection style. 
Clicking the 'Radio Button' control causes the image list to be displayed as a set or radio buttons.
Clicking the 'Drop Down' control causes the image list to be displayed as a drop-down list.
In either case, the markup for the image selection control is downloaded through a resource request and only the affected control is updated.

If you select an image, the portlet sets a public render parameter indicating the image to be displayed.

####The PH Color Selection Portlet

The upper portion of the portlet contains an enty field that alllows you to enter a 
color value in CSS hexidecimal format, for example '#FFA' or '#CAFEBA'. The portlet
JavaScript code uses the portlet hub API to set a public render parameter contolling the background color
of items in the PH Resource Portlet and the PH Message Box Portlet.

The lower portion of the portlet contains a form with some controls and an entry field.
Text entered into the entry field is sent as a message to the PH Message Box Portlet.
The checkbox controls select the foreground color for the message text.
The state of the controls is maintained through use of private render parameters that are 
set by the portlet JavaScript code.

####The PH Resource Portlet

This portlet reacts to the public render parameters set by the PH Image Selection Portlet
and the PH Color Selection Portlet. The portlet hub informs the PH Resource Portlet when
the public render parameters change. When informed of a change, the portlet creates a new resource URL 
and fetches new markup to display an appropritate image with the specified background color.

When the cacheability setting is changed, the portlet creates a resource URL with the selected
cacheability setting and retrieves the markup. 
The serveResource method executed on the server produces markup appropriate to the available
page state on the resource URL. 
This can be seen especially well when a background color is selected.

The 'Set border' checkbox causes the portlet client-side JavaScript code to set a resource parameter
on the resource URL used to retrieve markup. The serveResource method returns markup containing a border
when the resource parameter is set on the URL.

The sole purpose of the 'Action' button in the resource markup is
to provide a visual indication that an action URL can be created when cacheability is set to 'PAGE'.
Clicking the button has no effect.


####The PH Message Box Portlet

This portlet displays messages sent via server-side portlet events sent from the 
PH Color Selection Portlet. When the PH Message Box Portlet receives an event, it updates
a private render parameter. The portlet hub notifies the PH Message Box Portlet of the change.
The message box portlet client side code then creates a resource URL to retrieve the markup.

The portlet simulates a persistent message store by putting the messages into the portlet session, so
when a new session begins, the messages are cleared. 
You can also clear the messages by clicking the 'clear' button.

###Back-Button Support

The portlet hub provided by the IBM Single-Page Application Theme Module for WebSphere Portal
stores the URL representing the page state in the browser address bar and the browser history 
with every page state change. 
This allows you to move backward and forward through state changes on the page without
causing a page refresh.

Note that you cannot undo an action through use of the back button.
This means in particular that you cannot make a message disappear through use of
back / forward buttons.

###Source Code Description

Each of the sample portlets consists of a Java class that extends GenericPortlet 
to handle the server-side requests along with a JSP that renders the portlet markup.
This is standard JSR 286 portlet code, so it will not be described in more detail. 

The portlet client-side JavaScript code (known as the 'portlet client' in JSR 362 parlance) is provided through the JSP.
The portlet client for each of the sample portlets has a similar structure. 
We will examine this structure in more detail taking the PH Image Selection Portlet, view-isp.jsp, as an example.

Portlet client execution begins when the portlet client registers itself with the portlet hub.

```JavaScript
// Register with Portlet Hub, add listener for onStateChange event
wpModules.portlethub.register(pid).then(function (pi) {
   console.log("ISP Image Selection Portlet: registered: " + pid);
   hub = pi;
   currState = hub.newState();
   hub.addEventListener("portlet.onStateChange", update);
});
```

###Portlet Hub Documentation

The IBM Single-Page Application Theme Module for WebSphere Portal provides 
documentation for the portlet hub JavaScript API.

###Common Problems

If you deploy the sample portlets and configure the page according to the instructions, but the PH Resource Portlet
does not display an image, the IBM Single-Page Application Theme Module for WebSphere Portal
might not be installed properly.

To verify this, check your browser's JavaScript console log.
Open the console and refresh the page. 
If you see  a string similar to "wp.portalsamples.modules.PortletHub, v0.1, 2015/07/25, 20150725-203729",
the theme module is installed correctly.

If you do not see the version string, check the theme module installation. 

###Support

Please use the 'issues' link associated with this repository to provide feedback
or report problems. 
We will do our best to assist you promptly.

###License
This code and API documentation is provided under the [Apache 2.0 License] 
(http://www.apache.org/licenses/LICENSE-2.0.html). 
