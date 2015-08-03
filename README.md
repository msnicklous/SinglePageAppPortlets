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

[Single-Page App Page](/portalpage.gif)

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

The PH Image Selection Portlet uses a private render parameter to control the selection 
style. 

####The PH Color Selection Portlet

####The PH Resource Portlet Portlet

####The PH Message Box Portlet

####Back-Button Support

###Source Code Description



###Support

Please use the 'issues' link associated with this repository to provide feedback
or report problems. 
We will do our best to assist you promptly.

###License
This code and API documentation is provided under the [Apache 2.0 License] 
(http://www.apache.org/licenses/LICENSE-2.0.html). 
