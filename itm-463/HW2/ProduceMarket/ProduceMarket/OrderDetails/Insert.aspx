<%@ Page Title="OrderDetailsInsert" Language="C#" MasterPageFile="~/OrderMasterPage.Master" CodeBehind="Insert.aspx.cs" Inherits="ProduceMarket.OrderDetails.Insert" %>
<asp:Content runat="server" ContentPlaceHolderID="MainContent">
    <div>
		<p>&nbsp;</p>
        <asp:FormView runat="server"
            ItemType="ProduceMarket.Models.OrderDetails" DefaultMode="Insert"
            InsertItemPosition="FirstItem" InsertMethod="InsertItem"
            OnItemCommand="ItemCommand" RenderOuterTable="false">
            <InsertItemTemplate>
                <fieldset class="form-horizontal">
				<legend>Insert OrderDetails</legend>
		        <asp:ValidationSummary runat="server" CssClass="alert alert-danger" />
							<asp:DynamicControl Mode="Insert" 
								DataField="OrderId" 
								DataTypeName="ProduceMarket.Models.Orders" 
								DataTextField="OrderId" 
								DataValueField="OrderId" 
								UIHint="ForeignKey" runat="server" />
						    <asp:DynamicControl Mode="Insert" DataField="Quantity" runat="server" />
						    <asp:DynamicControl Mode="Insert" DataField="Price" runat="server" />
						    <asp:DynamicControl Mode="Insert" DataField="Produce" runat="server" />
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <asp:Button runat="server" ID="InsertButton" CommandName="Insert" Text="Insert" CssClass="btn btn-primary" />
                            <asp:Button runat="server" ID="CancelButton" CommandName="Cancel" Text="Cancel" CausesValidation="false" CssClass="btn btn-default" />
                        </div>
					</div>
                </fieldset>
            </InsertItemTemplate>
        </asp:FormView>
    </div>
</asp:Content>
