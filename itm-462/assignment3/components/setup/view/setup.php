<?php defined("true-access") or die;

/*
* Main function
*/
function view($data) {
    renderPage('setup', function($data) {
        $fontSize='15px/20px';
        $fontNames="'2DumbRegular', Arial, sans-serif";
        $color="#000000";
        hr();
        try {
            set_time_limit(1);
            if (!databaseExists(DB_NAME) || $data['reset']) {
                if ($data['reset']) {
                    stylizedDiv('Restoring '.DB_NAME.' database ...', $fontSize,$color, $fontNames);
                } else {
                    stylizedDiv(DB_NAME.' does not exist ...', $fontSize, $color, $fontNames);
                    stylizedDiv('Creating '.DB_NAME.'...', $fontSize, $color, $fontNames);
                }
                sqlImport('content/sql/install.sql');
                if ($data['reset']) {
                    stylizedDiv(DB_NAME.' restored.', $fontSize, $color, $fontNames);
                } else {
                    stylizedDiv(DB_NAME." created successfully.", $fontSize, $color, $fontNames);
                }
            } else {
                stylizedDiv(DB_NAME.' already exists.', $fontSize, $color, $fontNames);
            }
        } catch (Exception $e) {
            stylizedDiv('Exception caught: ' . $e->getMessage());
        }
        hr();
        renderSetupButtons();
    }, $data);
}

/**
 * Output the setup reset buttons visible
 * on the setup component install view.
 */
function renderSetupButtons() {
    echo '<div id="resetButtonContainer" class="clsNavButtonContainer">'.PHP_EOL;
    if (isComponentEnabled('setup','reset')) {
        echo '    <div class="clsNavButton1">'.PHP_EOL;
        echo '        <div class="clsCurrentPage">'.PHP_EOL;
        echo '          '.setupResetLink().PHP_EOL;
        echo '        </div>'.PHP_EOL;
        echo '    </div>'.PHP_EOL;
    }
    renderSetupIndexButton();
    echo '</div>'.PHP_EOL;
}

/**
 * Output the setup "index.php" button visible
 * on the setup component install view.
 */
function renderSetupIndexButton () {
    echo '    <div class="clsNavButton1">'.PHP_EOL;
    echo '        <div class="clsCurrentPage">'.PHP_EOL;
    echo '          '.setupIndexLink().PHP_EOL;
    echo '        </div>'.PHP_EOL;
    echo '    </div>'.PHP_EOL;
}

/**
 * Return the HTML for the setup database reset link.
 * @return string
 */
function setupResetLink() {
    return linkToString("index.php?option=setup&view=reset","Reset Database","Reset Database link","clsNoWrap");
}

/**
 * Return the HTML for the setup database reset link.
 * @return string
 */
function setupIndexLink() {
    return linkToString("index.php","index.php","Reload App","clsNoWrap");
}

?>

