<%@ Page Title="OrderDetails Details" Language="C#" MasterPageFile="~/OrderMasterPage.Master" CodeBehind="Details.aspx.cs" Inherits="ProduceMarket.OrderDetails.Details" %>
<asp:Content runat="server" ContentPlaceHolderID="MainContent">
    <div>
		<p>&nbsp;</p>
      
        <asp:FormView runat="server"
            ItemType="ProduceMarket.Models.OrderDetails" DataKeyNames="OrderDetailId"
            SelectMethod="GetItem"
            OnItemCommand="ItemCommand" RenderOuterTable="false">
            <EmptyDataTemplate>
                Cannot find the OrderDetails with OrderDetailId <%: Request.QueryString["OrderDetailId"] %>
            </EmptyDataTemplate>
            <ItemTemplate>
                <fieldset class="form-horizontal">
                    <legend>OrderDetails Details</legend>
							<div class="row">
								<div class="col-sm-2 text-right">
									<strong>OrderDetailId</strong>
								</div>
								<div class="col-sm-4">
									<asp:DynamicControl runat="server" DataField="OrderDetailId" ID="OrderDetailId" Mode="ReadOnly" />
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2 text-right">
									<strong>OrderId</strong>
								</div>
								<div class="col-sm-4">
									<%#: Item.Order != null ? Item.Order.OrderId : 0 %>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2 text-right">
									<strong>Quantity</strong>
								</div>
								<div class="col-sm-4">
									<asp:DynamicControl runat="server" DataField="Quantity" ID="Quantity" Mode="ReadOnly" />
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2 text-right">
									<strong>Price</strong>
								</div>
								<div class="col-sm-4">
									<asp:DynamicControl runat="server" DataField="Price" ID="Price" Mode="ReadOnly" />
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2 text-right">
									<strong>Produce</strong>
								</div>
								<div class="col-sm-4">
									<asp:DynamicControl runat="server" DataField="Produce" ID="Produce" Mode="ReadOnly" />
								</div>
							</div>
                 	<div class="row">
					  &nbsp;
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<asp:Button ID="CancelButton" runat="server" CommandName="Cancel" Text="Back" CssClass="btn btn-default" />
						</div>
					</div>
                </fieldset>
            </ItemTemplate>
        </asp:FormView>
    </div>
</asp:Content>

