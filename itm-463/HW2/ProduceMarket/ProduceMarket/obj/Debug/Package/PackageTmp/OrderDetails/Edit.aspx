<%@ Page Title="OrderDetailsEdit" Language="C#" MasterPageFile="~/OrderMasterPage.Master" CodeBehind="Edit.aspx.cs" Inherits="ProduceMarket.OrderDetails.Edit" %>
<asp:Content runat="server" ContentPlaceHolderID="MainContent">
    <div>
		<p>&nbsp;</p>
        <asp:FormView runat="server"
            ItemType="ProduceMarket.Models.OrderDetails" DefaultMode="Edit" DataKeyNames="OrderDetailId"
            UpdateMethod="UpdateItem" SelectMethod="GetItem"
            OnItemCommand="ItemCommand" RenderOuterTable="false">
            <EmptyDataTemplate>
                Cannot find the OrderDetails with OrderDetailId <%: Request.QueryString["OrderDetailId"] %>
            </EmptyDataTemplate>
            <EditItemTemplate>
                <fieldset class="form-horizontal">
                    <legend>Edit OrderDetails</legend>
					<asp:ValidationSummary runat="server" CssClass="alert alert-danger"  />                 
							<asp:DynamicControl Mode="Edit" 
								DataField="OrderId" 
								DataTypeName="ProduceMarket.Models.Orders" 
								DataTextField="OrderId" 
								DataValueField="OrderId" 
								UIHint="ForeignKey" runat="server" />
						    <asp:DynamicControl Mode="Edit" DataField="Quantity" runat="server" />
						    <asp:DynamicControl Mode="Edit" DataField="Price" runat="server" />
						    <asp:DynamicControl Mode="Edit" DataField="Produce" runat="server" />
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
							<asp:Button runat="server" ID="UpdateButton" CommandName="Update" Text="Update" CssClass="btn btn-primary" />
							<asp:Button runat="server" ID="CancelButton" CommandName="Cancel" Text="Cancel" CausesValidation="false" CssClass="btn btn-default" />
						</div>
					</div>
                </fieldset>
            </EditItemTemplate>
        </asp:FormView>
    </div>
</asp:Content>

