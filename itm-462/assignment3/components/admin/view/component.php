<?php defined("true-access") or die;

/*
* Main function
*/
function view($data) {
    renderPage('admin', 'renderComList');
}

/**
 * Output the component list HTML.
 */
function renderComList() {
    if (isComponentEnabled('admin', 'siteupdate')) {
        h3('Site Update');
        renderSiteEditForm();
    }
    br();
    hr();
    h3('Component List');
    $summaryHTML = getComList();
    foreach($summaryHTML as $line) {
        echo $line;
    }
}

/**
 * Output HTML for the Site Title / SubTitle Editor Form.
 */
function renderSiteEditForm() {
    echo '<form action="index.php?option=admin&view=siteupdate" method="post">'.PHP_EOL;
    echo '<table style="background-color: #fbeed5;" border="1" padding="5"><tr><th>Title</th><th>SubTitle</th></tr>'.PHP_EOL;
    echo '<tr><td><input class="clsFormField" type="text" size="50" placeholder="'.getSiteConfig('title').'" name="title"/></td>'.PHP_EOL;
    echo '<td><input type="text" size="50" placeholder="'.getSiteConfig('subtitle').'" name="subtitle"/></td></tr>'.PHP_EOL;
    echo '<tr><td colspan="2" style="text-align: right;"><input type="submit" value="update"/></td></tr>                     '.PHP_EOL;
    echo '</table>'.PHP_EOL;
    echo '</form>'.PHP_EOL;

}






