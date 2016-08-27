<%@ Page Title="OrderDetailsList" Language="C#" MasterPageFile="~/OrderMasterPage.Master" CodeBehind="Default.aspx.cs" Inherits="ProduceMarket.OrderDetails.Default" %>

<%@ Register TagPrefix="FriendlyUrls" Namespace="Microsoft.AspNet.FriendlyUrls" %>
<asp:Content runat="server" ContentPlaceHolderID="MainContent">
    <asp:Label ID="OrderDetailTitle" runat="server">
        <div class="row">
            <h3>Order Details</h3>
        </div>    
    </asp:Label>    
    <p>
        <asp:HyperLink ID="AddOrderDetailLink" runat="server" NavigateUrl="Insert" Text="Create new" />
    </p>
    <div>
        <asp:ListView ID="OrderDetailsListView" runat="server"
            DataKeyNames="OrderDetailId"
            ItemType="ProduceMarket.Models.OrderDetails"
            SelectMethod="GetData">
            <EmptyDataTemplate>
                There are no entries found for OrderDetails
            </EmptyDataTemplate>
            <LayoutTemplate>
                <table class="table">
                    <thead>
                        <tr>
                            <th>
                            </th>
                            <th>
                                <asp:LinkButton Text="OrderDetailId" CommandName="Sort" CommandArgument="OrderDetailId" runat="Server" />
                            </th>
                            <th>
                                <asp:LinkButton Text="OrderId" CommandName="Sort" CommandArgument="OrderId" runat="Server" />
                            </th>
                            <th>
                                <asp:LinkButton Text="Quantity" CommandName="Sort" CommandArgument="Quantity" runat="Server" />
                            </th>
                            <th>
                                <asp:LinkButton Text="Price" CommandName="Sort" CommandArgument="Price" runat="Server" />
                            </th>
                            <th>
                                <asp:LinkButton Text="Produce" CommandName="Sort" CommandArgument="Produce" runat="Server" />
                            </th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr runat="server" id="itemPlaceholder" />
                    </tbody>
                </table>
                <asp:DataPager PageSize="5" runat="server">
                    <Fields>
                        <asp:NextPreviousPagerField ShowLastPageButton="False" ShowNextPageButton="False" ButtonType="Button" ButtonCssClass="btn" />
                        <asp:NumericPagerField ButtonType="Button" NumericButtonCssClass="btn" CurrentPageLabelCssClass="btn disabled" NextPreviousButtonCssClass="btn" />
                        <asp:NextPreviousPagerField ShowFirstPageButton="False" ShowPreviousPageButton="False" ButtonType="Button" ButtonCssClass="btn" />
                    </Fields>
                </asp:DataPager>
            </LayoutTemplate>
            <ItemTemplate>
                <tr>
                    <td>
                        <asp:LinkButton ID="lnkSelectDetail" Text="Select" CommandName="Select" runat="server" />
                    </td>
                    <td>
                        <asp:DynamicControl runat="server" DataField="OrderDetailId" ID="OrderDetailId" Mode="ReadOnly" />
                    </td>
                    <td>
                        <%#: Item.Order != null ? Item.Order.OrderId : 0 %>
                    </td>
                    <td>
                        <asp:DynamicControl runat="server" DataField="Quantity" ID="Quantity" Mode="ReadOnly" />
                    </td>
                    <td>
                        <asp:DynamicControl runat="server" DataField="Price" ID="Price" Mode="ReadOnly" />
                    </td>
                    <td>
                        <asp:DynamicControl runat="server" DataField="Produce" ID="Produce" Mode="ReadOnly" />
                    </td>
                    <td>
                        <asp:HyperLink runat="server" NavigateUrl='<%# FriendlyUrl.Href("~/OrderDetails/Details", Item.OrderDetailId) %>' Text="Details" />
                        | 
					    <asp:HyperLink runat="server" NavigateUrl='<%# FriendlyUrl.Href("~/OrderDetails/Edit", Item.OrderDetailId) %>' Text="Edit" />
                        | 
                        <asp:HyperLink runat="server" NavigateUrl='<%# FriendlyUrl.Href("~/OrderDetails/Delete", Item.OrderDetailId) %>' Text="Delete" />
                    </td>
                </tr>
            </ItemTemplate>
            <SelectedItemTemplate>
                <tr style="background-color: #E9E9E9;">
                    <td>
                        <asp:LinkButton ID="lnkSelectDetail" Text="Select" CommandName="Select" runat="server" />
                    </td>
                    <td>
                        <asp:DynamicControl runat="server" DataField="OrderDetailId" ID="OrderDetailId" Mode="ReadOnly" />
                    </td>
                    <td>
                        <%#: Item.Order != null ? Item.Order.OrderId : 0 %>
                    </td>
                    <td>
                        <asp:DynamicControl runat="server" DataField="Quantity" ID="Quantity" Mode="ReadOnly" />
                    </td>
                    <td>
                        <asp:DynamicControl runat="server" DataField="Price" ID="Price" Mode="ReadOnly" />
                    </td>
                    <td>
                        <asp:DynamicControl runat="server" DataField="Produce" ID="Produce" Mode="ReadOnly" />
                    </td>
                    <td>
                        <asp:HyperLink runat="server" NavigateUrl='<%# FriendlyUrl.Href("~/OrderDetails/Details", Item.OrderDetailId) %>' Text="Details" />
                        | 
					    <asp:HyperLink runat="server" NavigateUrl='<%# FriendlyUrl.Href("~/OrderDetails/Edit", Item.OrderDetailId) %>' Text="Edit" />
                        | 
                        <asp:HyperLink runat="server" NavigateUrl='<%# FriendlyUrl.Href("~/OrderDetails/Delete", Item.OrderDetailId) %>' Text="Delete" />
                    </td>
                </tr>
            </SelectedItemTemplate>
        </asp:ListView>
    </div>
</asp:Content>

