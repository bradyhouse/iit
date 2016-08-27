<%@ Page Title="OrdersDelete" Language="C#" MasterPageFile="~/CustomerMasterPage.Master" CodeBehind="Delete.aspx.cs" Inherits="ProduceMarket.Orders.Delete" %>
<asp:Content runat="server" ContentPlaceHolderID="MainContent">
    <div>
		<p>&nbsp;</p>
        <h3>Are you sure want to delete this Orders?</h3>
        <asp:FormView runat="server"
            ItemType="ProduceMarket.Models.Orders" DataKeyNames="OrderId"
            DeleteMethod="DeleteItem" SelectMethod="GetItem"
            OnItemCommand="ItemCommand" RenderOuterTable="false">
            <EmptyDataTemplate>
                Cannot find the Orders with OrderId <%: Request.QueryString["OrderId"] %>
            </EmptyDataTemplate>
            <ItemTemplate>
                <fieldset class="form-horizontal">
                    <legend>Delete Orders</legend>
							<div class="row">
								<div class="col-sm-2 text-right">
									<strong>OrderId</strong>
								</div>
								<div class="col-sm-4">
									<asp:DynamicControl runat="server" DataField="OrderId" ID="OrderId" Mode="ReadOnly" />
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2 text-right">
									<strong>CustomerId</strong>
								</div>
								<div class="col-sm-4">
									<%#: Item.Customer != null ? Item.Customer.Name : "" %>
								</div>
							</div>
                 	<div class="row">
					  &nbsp;
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<asp:Button ID="DeleteButton" runat="server" CommandName="Delete" Text="Delete" CssClass="btn btn-danger" />
							<asp:Button ID="CancelButton" runat="server" CommandName="Cancel" Text="Cancel" CssClass="btn btn-default" />
						</div>
					</div>
                </fieldset>
            </ItemTemplate>
        </asp:FormView>
    </div>
</asp:Content>

