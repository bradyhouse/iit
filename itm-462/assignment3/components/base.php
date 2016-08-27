<?php defined("true-access") or die;

/**
 * Collection of functions used across components.
 */

// ToDo: Convert to a class exposing a set of static methods

/**
 * Rendering page template.  This is really the entry point
 * to the other functions on this page.
 *
 * @param $header
 * @param $bodyFn
 * @param string $args
 */
function renderPage($header, $bodyFn, $args='' ) {
    startOfPage();
    startContent();
    switch($header) {
        case 'setup':
            banner(SETUP_SITE_TITLE, SETUP_SITE_SUBTITLE);
            br();
            pageNavButtons($header);
            startInnerPageContent();
            break;
        default:
            banner(getSiteConfig('title'),getSiteConfig('subtitle'));
            br();
            pageNavButtons($header);
            startInnerPageContent();
            renderLoginForm();

            break;
    }
    $bodyFn($args);
    endInnerPageContent();
    pageFooter();
    endContent();
    endOfPage();
}

/**
 * Output the start of page tags.
 */
function startOfPage() {
	echo '<!doctype html> '.PHP_EOL;
	echo '<html>          '.PHP_EOL;
    echo '<head>'.PHP_EOL;
    echo '<title>'.PAGE_TITLE.'</title>'.PHP_EOL;
    echo '<link href="content/css/bootstrap.min.css" rel="stylesheet">'.PHP_EOL;
    echo '<link href="content/css/bootstrap-responsive.min.css" rel="stylesheet">'.PHP_EOL;
    echo '<link rel="stylesheet" href="content/css/960.css" />'.PHP_EOL;
    echo '<link rel="stylesheet" href="content/css/styles.css" />'.PHP_EOL;
    echo '<script src="content/js/three.min.js"></script>'.PHP_EOL;
    echo '<script src="content/js/bling.js"></script>'.PHP_EOL;
    echo '</head>'.PHP_EOL;
	echo '<body onLoad="onLoad();">'.PHP_EOL;
}

/**
 *
 * Output the closing body and html tags.
 */
function endOfPage() {

    echo '</body> '.PHP_EOL;
	echo '</html> '.PHP_EOL;
}

/**
 * Output an H1 tag containing the supplied text.
 *
 * @param $content
 */
function siteTitle($content) {
	echo "<h1><a style='color:red' href='index.php'>".$content."<a></h1>".PHP_EOL;
}

/**
 * Output an opening article tag.
 */
function startContent() {
    echo '<div class="container_12">'.PHP_EOL;
    echo '<div class="grid_12">'.PHP_EOL;
}

/**
 * Output a closing article tag.
 */
function endContent() {
    echo '</div>'.PHP_EOL;
    echo '</div>'.PHP_EOL;
}

/**
 * Output an opening aside tag.
 */
function startAside() {
	echo '<aside>'.PHP_EOL;
}

/**
 * Output a closing aside tag.
 */
function endAside() {
	echo '</aside>'.PHP_EOL;
}

/**
 * Output an H1 tag containing the requested text.
 *
 * @param $content
 */
function h1($content) {
	echo "<h1>".$content."</h1>".PHP_EOL;
}

/**
 * Output an H3 tag containing the requested content.
 *
 * @param $content
 */
function h3($content) {
	echo "<h3>".$content."</h3>".PHP_EOL;
}

/**
 * Output an H2 tag containing the requested content.
 *
 * @param $content
 */
function h2($content) {
	echo "<h2>".$content."</h2>".PHP_EOL;
}

/**
 * Output a p tag containing the requested text.
 *
 * @param $content
 */
function p($content) {

	echo "<p>".$content."</p>".PHP_EOL;
}

/**
 * Output an HTML paragraph tag containing the requested text and
 * using the optional font size and color.
 *
 * @param $content
 * @param $px
 * @param $color
 */
function stylizedDiv($content, $fontsize='80px/100px', $color='#000000', $fontnames='\'3DumbRegular\', Arial, sans-serif') {
    echo '<div style="font: '.$fontsize.' '.$fontnames.';  color: '.$color.'">'.PHP_EOL;
    p($content);
    echo '</div>'.PHP_EOL;
}

/**
 * Output an HTML hr tag.
 */
function hr() {
    echo '<hr />'.PHP_EOL;
}

/**
 * Return a line break HTML tag.
 * @return string
 */
function br() {
    echo '<BR />';
}

/**
 * Output an HTML hyperlink with the requested parameters.
 *
 * @param $url
 * @param $text
 * @param $title
 */
function linkToString($url, $text, $title, $class="clsCustomLink") {
    return '<a href="'.$url.'" title="'.$title.'" class="'.$class.'" >'.$text.'</a>';
}

/**
 * Output a single row HTML table
 * using the supplied array to
 * populate the columns.
 *
 * @param $array
 */
function hbar($cells) {
    echo '<table><tr>'.PHP_EOL;
    foreach($cells as $cell) {
        echo '<td nowrap>'.$cell.'</td>'.PHP_EOL;
    }
    echo '</tr></table>'.PHP_EOL;
}

/**
 * Output banner displaying the
 * requested title and subtitle text.
 *
 * @param $title
 * @param $subTitle
 */
function banner($title, $subTitle) {
    echo '<div class="clsPageHeading">'.PHP_EOL;
    echo '<div class="container_12">'.PHP_EOL;
    echo '<div class="grid_12">'.PHP_EOL;
    echo '<table style="margin-left: 3    0px; margin-bottom: 20px;">'.PHP_EOL;
    echo '    <tr>'.PHP_EOL;
    echo '       <td rowspan="2" style="text-align: left;">'.PHP_EOL;
    echo '           <div id="blingContainer" style="width:180px; height:180px; float: left;"></div>'.PHP_EOL;
    echo '       </td>'.PHP_EOL;
    echo '       <td style="text-align: left; vertical-align: bottom; color: #CC9933;">'.PHP_EOL;
    echo '           '.$title.PHP_EOL;
    echo '       </td>'.PHP_EOL;
    echo '     </tr>'.PHP_EOL;
    echo '     <tr>'.PHP_EOL;
    echo '       <td>'.PHP_EOL;
    stylizedDiv($subTitle,"15px/20px", "#000000",'\'2DumbRegular\'');
    echo '       </td>'.PHP_EOL;
    echo '     </tr>'.PHP_EOL;
    echo '</table>'.PHP_EOL;
    echo '</div>'.PHP_EOL;
    echo '</div>'.PHP_EOL;
    echo '</div>'.PHP_EOL;
}

/**
 * Output the Inner page content
 * div opening HTML tag.
 */
function startInnerPageContent() {
    echo '<div id="PageContent">'.PHP_EOL;
    echo '<div id="inner_page_content">'.PHP_EOL;
}

/**
 * Output the inner page content
 * div closing HTML tag.
 */
function endInnerPageContent() {
    echo '</div>'.PHP_EOL;
    echo '</div>'.PHP_EOL;
}

/**
 * Output the navigation buttons visible
 * at the top of each page.  Based on
 * the select criteria, the associated
 * link button will be highlighted to
 * indicate the current page.
 *
 * @param string $select
 */
function pageNavButtons ($select='none') {
    echo '<div id="divButtonContainer" class="clsNavButtonContainer">'.PHP_EOL;
    if (isComponentEnabled('course','list')) {
        echo '    <div class="clsNavButton1">'.PHP_EOL;
        echo '        <div class="'.($select == 'courselist' ? 'clsCurrentPage' : 'clsOtherPage').'">'.PHP_EOL;
        echo courseListLink().PHP_EOL;
        echo '        </div>'.PHP_EOL;
        echo '    </div>'.PHP_EOL;
    }
    if (isComponentEnabled('user','list')) {
        echo '    <div class="clsNavButton2">'.PHP_EOL;
        echo '        <div  class="'.($select == 'userlist' ? 'clsCurrentPage' : 'clsOtherPage').'">'.PHP_EOL;
        echo userListLink().PHP_EOL;
        echo '        </div>'.PHP_EOL;
        echo '    </div>'.PHP_EOL;
    }
    if ($select == 'admin' || isComponentEnabled('admin','list')) {
        echo '    <div class="clsNavButton3">' . PHP_EOL;
        echo '        <div class="' . ($select == 'admin' ? 'clsCurrentPage' : 'clsOtherPage') . '">' . PHP_EOL;
        echo adminLink() . PHP_EOL;
        echo '        </div>' . PHP_EOL;
        echo '    </div>' . PHP_EOL;
    }

    if(isComponentEnabled('setup','list')) {
        echo '    <div class="clsNavButton4">'.PHP_EOL;
        echo '        <div class="'.($select == 'setup' ? 'clsCurrentPage' : 'clsOtherPage').'">'.PHP_EOL;
        echo setupLink().PHP_EOL;
        echo '        </div>'.PHP_EOL;
        echo '    </div>'.PHP_EOL;
    }
    echo '</div>'.PHP_EOL;
}

/**
 * Output HTML for site links.
 *
 * Note - Originally added to create site links
 * displayed at the bottom of each page of the
 * site.
 *
 */
function siteLinks() {
    $links = array();
    if(isComponentEnabled('course', 'list')) {
        array_push($links, courseListLink());
    }
    if(isComponentEnabled('user', 'list')) {
        array_push($links, userListLink());
    }
    if(isComponentEnabled('admin', 'list')) {
        array_push($links, adminLink());
    }
    if(isComponentEnabled('setup', 'list')) {
        array_push($links, setupLink());
    }
    hbar($links);
}

/**
 * Output the page footer HTML.
 */
function pageFooter() {
    echo '<div id="PageFooter" >'.PHP_EOL;
    siteLinks();
    echo ' </div>'.PHP_EOL;
}

/**
 * Return the HTML for a course list link.
 * @return string
 */
function courseListLink($displayText='') {
    if (empty($displayText)) {
        return linkToString("index.php?option=course&view=list","Courses","Courses link","clsNone");
    } else {
        return linkToString("index.php?option=course&view=list",$displayText,$displayText." link");
    }
}

/**
 * Return the HTML for a course list link.
 * @return string
 */
function setupLink() {
    return linkToString("index.php?option=setup&view=install","Setup","Setup link","clsNone");
}

/**
 * Return the HTML for a login link.
 * @return string
 */
function loginLink() {
    return linkToString("index.php?option=store&view=login","Login","Login link","clsNone");
}

/**
 * Return the HTML for an admin link.
 * @return string
 */
function adminLink() {
    return linkToString("index.php?option=admin&view=comlist","Admin","Admin link","clsNone");
}

/**
 * Return the HTML for an user list link.
 *
 * @param string $displayText
 * @return string
 */
function userListLink($displayText='') {
    if (empty($displayText)) {
        return linkToString("index.php?option=user&view=list","Users","Users link","clsNone");
    } else {
        return linkToString("index.php?option=user&view=list",$displayText,$displayText." link");
    }
}

/**
 * Output the login form (or greeting)
 * HTML.
 */
function renderLoginForm() {

    if (isUserAuthenticated()) {
        echo '<table><tr><td>'.PHP_EOL;
        stylizedDiv('Hello, '.$_SESSION['user'].'&nbsp;&nbsp;','15px/20px');
        echo '</td><td>'.PHP_EOL;
        echo linkToString("index.php?option=store&view=logout","LogOut","Logout").PHP_EOL;
        echo '</td></tr></table>'.PHP_EOL;
        hr();
    } else {
        echo '<form action="index.php?option=store&view=login" method="post">'.PHP_EOL;
        echo '	<input type="text" placeholder="username" name="username"/>'.PHP_EOL;
        echo '	<input type="password" placeholder="password" name="password"/>'.PHP_EOL;
        echo '	<input type="submit" value="Login"/>                       '.PHP_EOL;
        echo '</form>                                                      '.PHP_EOL;
        hr();
    }
}

/**
 * Output Error message HTML.
 *
 * @param $header
 * @param $data
 */
function renderError($header, $data) {
    renderPage($header, function($data) {
        stylizedDiv($data['header'].' '.$data['subheader'], "40px",'#710707');
        if (!empty($data['message'])) {
            stylizedDiv($data['message'], "20px", "#710707");
        }
    }, $data);
}

/**
 * Output a non-blank space HTML character.
 */
function nbsp() {
    echo '&nbsp;'.PHP_EOL;
}

?>