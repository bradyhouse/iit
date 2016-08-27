﻿<%@ Control Language="C#" CodeBehind="MultilineText_Edit.ascx.cs" Inherits="ProduceMarket.MultilineText_EditField" %>

<div id="Div1" runat="server" class="form-group">
    <asp:Label ID="Label1" runat="server" CssClass="col-sm-2 control-label" />
    <div class="col-sm-3">
		<asp:TextBox ID="TextBox1" TextMode="MultiLine" Rows="3" type="Text" runat="server" Text='<%# FieldValueEditString %>' CssClass="form-control DDTextBox"></asp:TextBox>
    </div>
</div>
