Lab 2
======

By the end of this Lab you will have a model solution for the first assignment, and added to that additional animation features. This lab is graded, and is due a week from when the content is posted. It should take approximately 2-3 hours to complete, including watching the video. It is worth a total of 50pts – 7% of course grade.

Listen to the attached video that covers each section, and when indicated, pause the video and complete the exercise for that section.  Each section begins on a new page – there are 3 sections.

- Extract the included supplemental www folder and use that folder (next to a web server downloaded for a1) and work within that www folder. Once complete, zip up your www folder containing lab2 and submit that to blackboard.
- Place all answer files and source code that you create during this lab within the www folder.

### Required Software:

- A plain text editor
- A web browser with developer tools.
- A downloaded web server from blackboard

### Section 1 Model Solution

A prerequisite for understanding this code is the previous 5 weeks. You need to be familiar with variables, functions, loops and the jquery selector. See content in previous lectures and labs

- You will additionally need a working web server
- You will also need a setup text editor

#### Steps

- Review the video
- Take a look at the JSON data – make sure you can locate the following elements within the javascript file
a. Build our object definitions
b. Parse the JSON data
c. Render the data from the recipes
d. Create a combination function
e. Render results of calculation on the page

- Modify the render functions present around lines 181, 200 & 240 - these functions create HTML from our objects - but the style on this page is bland and hard to read. You will modify these functions to produce a more visually coherent solution.
- Use CSS float and clear or alternatively the bootstrap grid system to horizontally align recipe lists to a flexible grid (http://getbootstrap.com/css/#grid)
- Use CSS background, border radius and border or alternatively bootstrap labels to colorize the supplier names listed on recipes

### Section 2 Effects

jQuery Effects is a set of functions included in the core jQuery library - that requires no additional
download- that animates properties of css. We talked about animation being the change of a single
value or value(s) over time.

#### Steps

- Review the video
- Modify the a1.start function to first fade in a div that says "loading" - and when the ajax parsing function parseJSONdata returns, fadeOut the loading div "slowly", and after that fade finishes - slideDown() the div containing the recipe information.
- Modify the renderCalculator function to slideUp the recipes and slideDown the results of the recipe calculation - both animation functions should be chained together.

### Section 3 Animations

Functionality hints with css3 animations. jQuery is great for animations - but we can achieve the same or better results without needing a framework by using css3.

#### Steps

- Review the video
- Add to the a1.start function to create a logo div for the site that has a css class assigned to it that will cause the following animation to be assigned:
- 0% - left: 0px, transformation: rotate(0deg)
- 25% - left: 20px, transformation: rotate (10deg)
- 75% - left: 10px , transformation: rotate (15deg)
- 100% - left: 5px, transformation: rotate(0deg)
- Animation properties: basic with a 5 second duration and only 1 iteration.
- Design an animation (perhaps use inspiration from http://useyourinterface.com/) that animates at least two properties and apply it to the
- Calculate weekly total button defined inside the renderCalculator function

### Final Section Submission

Use a standard zipping program (winzip, linux zip, 7zip etc.) zip your working lab folder that contains all of your work. Submit your zip through Blackboard by the due date. Labs are due within the week they are available – there is no late submission

### Grade

???

### Feedback

???