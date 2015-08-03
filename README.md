# SinglePageAppPortlets

This repository contains portlets that use the IBM Single-Page Application Theme
Module for WebSphere Portal to update private and public render parameters and
execute portlet actions without causing a page refresh.

###Prerequisites

The portlets require WebSphere Portal version 8.5 with the IBM Single-Page
Application Theme Module for WebSphere Portal installed. The theme module is
available from the
[IBM Solutions Catalog](https://greenhouse.lotus.com/plugins/plugincatalog.nsf/home_full.xsp) site.

###Installation

1. Clone this repository. 
2. On your local machine switch to the 'SinglePageAppPortlets' directory under the repository location.
3. Build the project using the maven command 'maven install'.
4. Deploy the SinglePageAppPortlets.war file on your IBM WebSphere Portal server using WebSphere Portal tools.
5. Create a portal page for the single-page application. Two-column layout is recommended.
6. Place the 'PH Image Selection Portlet' and the 'PH Color Selection Portlet' portlets in the left column.
7. Place the 'PH Resource Portlet' and the 'PH Message Box Portlet' portlets in the right column.
8. Create a wire from the 'PH Color Selection Portlet' to the 'PH Message Box Portlet'.

###Usage



###License
This code and API documentation is provided under the [Apache 2.0 License] 
(http://www.apache.org/licenses/LICENSE-2.0.html). 
