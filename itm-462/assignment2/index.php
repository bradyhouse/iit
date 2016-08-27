<?php include 'template/send_email_top_php.html' ?>
<?php include 'template/doctype_section.html' ?>
<?php include 'template/open_html_tag_section.html' ?>
<?php include 'template/top_head_section.html' ?>
    <title>Spu Press - Home</title>
<?php include 'template/bottom_head_section.html' ?>
<?php include 'template/open_body_tag_section.html' ?>
<?php include 'template/open_page_container_section.html' ?>
<?php include 'template/banner_section.html' ?>
<?php include 'template/nav_home_section.html' ?>
<?php include 'template/open_page_content_section.html' ?>
    <div id="inner_page_content">
        <?php
            $f = 1;
            foreach (new DirectoryIterator('content') as $fileInfo) {
                if ($f <= 5) {
                    if($fileInfo->isDot()) continue;
                    if($fileInfo->getExtension() == "php") continue;
                    $header_array = split('[_]',$fileInfo->getFilename());
                    $epoch_time = $header_array[0];
                    $date_time_stamp = new DateTime("@$epoch_time");
                    $title_array = split('[.]', $header_array[1]);

                    if ( $file = fopen( $fileInfo->getPathname() , "r" ) )
                    {
                        $title = fgets ($file, 4092 );
                        echo "<h3>".$title."</h3>";
                        $line = array();
                        $i = 1;
                        while ( $line[$i] = fgets ($file, 4092) )
                        {
                            echo $line[$i];
                            $i++;
                        }
                        echo "<br /><br />";
                        echo "<div class=\"postDate\">Posted ".$date_time_stamp->format('Y-m-d H:i:s')."</div>";
                        echo "<hr /><br />";
                    }
                    $f++;
                }
            }
            if ($f == 1) {
                echo "<h6>Doh! No spu posted.</h6>";
            }
        ?>
    </div>
<?php include 'template/close_page_content_section.html' ?>
<?php include 'template/page_footer_section.html' ?>
<?php include 'template/close_page_container_section.html' ?>
<?php include 'template/send_email_form.html' ?>
<?php include 'template/error_dialog.html' ?>
<?php include 'template/scripts_section.html' ?>
<?php include 'template/close_body_tag_section.html' ?>
<?php include 'template/close_html_tag_section.html' ?>