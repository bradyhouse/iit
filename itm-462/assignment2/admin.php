<?php include 'template/send_email_top_php.html' ?>
<?php include 'template/doctype_section.html' ?>
<?php include 'template/open_html_tag_section.html' ?>
<?php include 'template/top_head_section.html' ?>
    <title>Spu Press - Admin</title>
<?php include 'template/bottom_head_section.html' ?>
<?php include 'template/open_body_tag_section.html' ?>
<?php include 'template/open_page_container_section.html' ?>
<?php include 'template/banner_section.html' ?>
<?php include 'template/nav_admin_section.html' ?>
<?php include 'template/open_page_content_section.html' ?>
    <div id="inner_page_content">
        <?php
            $auth_realm = 'My realm';
            require_once 'auth.php';
            echo "<h6>Hello, professor</h6>";
            echo '<p><a href="?action=logOut">LogOut</a>&nbsp;&nbsp;&nbsp;<a id="upload" href="#">Upload</a></p>';
            require_once 'file-upload.php';
        ?>
        <IFRAME SRC="content/index.php" frameborder="0" allowtransparency="true" WIDTH=100% HEIGHT=400 >
            If you can see this, your browser doesn't
            understand IFRAME.  However, we'll still
            <A HREF="content/index.php">link</A>
            you to the file.
        </IFRAME>
    </div>
<?php include 'template/close_page_content_section.html' ?>
<?php include 'template/page_footer_section.html' ?>
<?php include 'template/close_page_container_section.html' ?>
<?php include 'template/send_email_form.html' ?>
<?php include 'template/file_upload_form.html' ?>
<?php include 'template/error_dialog.html' ?>
<?php include 'template/scripts_section.html' ?>
<?php include 'template/close_body_tag_section.html' ?>
<?php include 'template/close_html_tag_section.html' ?>