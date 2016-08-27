<?php
    if(!isset($_SESSION)) {
        session_start();
    }
    if(isset($_SESSION['hits'])) {
        $hits = intval($_SESSION['hits']);
    } else {
        $hits = 0;
    }
    $hits++;
    $_SESSION['hits'] = strval($hits);
    session_write_close();
    echo "<div class=\"hitCount\">Hit Count:&nbsp;&nbsp;".$hits."&nbsp;<a href=\"hit-count-reset.php\" title=\"reset\">x</a></div>";

?>