﻿<%@ Page Title="CustomersList" Language="C#" MasterPageFile="~/Site.Master" CodeBehind="Default.aspx.cs" Inherits="ProduceMarket.Customers.Default" %>
<%@ Register TagPrefix="FriendlyUrls" Namespace="Microsoft.AspNet.FriendlyUrls" %>
<asp:Content runat="server" ContentPlaceHolderID="MainContent">
    <h2>Customers List</h2>
    <p>
        <asp:HyperLink runat="server" NavigateUrl="Insert" Text="Create new" />
    </p>
    <div>
        <asp:ListView id="ListView1" runat="server"
            DataKeyNames="CustomerId" 
			ItemType="ProduceMarket.Models.Customers"
            SelectMethod="GetData">
            <EmptyDataTemplate>
                There are no entries found for Customers
            </EmptyDataTemplate>
            <LayoutTemplate>
                <table class="table">
                    <thead>
                        <tr>
                            <th>
								<asp:LinkButton Text="Name" CommandName="Sort" CommandArgument="Name" runat="Server" />
							</th>
                            <th>
								<asp:LinkButton Text="Address" CommandName="Sort" CommandArgument="Address" runat="Server" />
							</th>
                            <th>
								<asp:LinkButton Text="City" CommandName="Sort" CommandArgument="City" runat="Server" />
							</th>
                            <th>
								<asp:LinkButton Text="State" CommandName="Sort" CommandArgument="State" runat="Server" />
							</th>
                            <th>
								<asp:LinkButton Text="Zip" CommandName="Sort" CommandArgument="Zip" runat="Server" />
							</th>
                            <th>
								<asp:LinkButton Text="Email" CommandName="Sort" CommandArgument="Email" runat="Server" />
							</th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr runat="server" id="itemPlaceholder" />
                    </tbody>
                </table>
				<asp:DataPager PageSize="5"  runat="server">
					<Fields>
                        <asp:NextPreviousPagerField ShowLastPageButton="False" ShowNextPageButton="False" ButtonType="Button" ButtonCssClass="btn" />
                        <asp:NumericPagerField ButtonType="Button"  NumericButtonCssClass="btn" CurrentPageLabelCssClass="btn disabled" NextPreviousButtonCssClass="btn" />
                        <asp:NextPreviousPagerField ShowFirstPageButton="False" ShowPreviousPageButton="False" ButtonType="Button" ButtonCssClass="btn" />
                    </Fields>
				</asp:DataPager>
            </LayoutTemplate>
            <ItemTemplate>
                <tr>
							<td>
								<asp:DynamicControl runat="server" DataField="Name" ID="Name" Mode="ReadOnly" />
							</td>
							<td>
								<asp:DynamicControl runat="server" DataField="Address" ID="Address" Mode="ReadOnly" />
							</td>
							<td>
								<asp:DynamicControl runat="server" DataField="City" ID="City" Mode="ReadOnly" />
							</td>
							<td>
								<asp:DynamicControl runat="server" DataField="State" ID="State" Mode="ReadOnly" />
							</td>
							<td>
								<asp:DynamicControl runat="server" DataField="Zip" ID="Zip" Mode="ReadOnly" />
							</td>
							<td>
								<asp:DynamicControl runat="server" DataField="Email" ID="Email" Mode="ReadOnly" />
							</td>
                    <td>
					    <asp:HyperLink runat="server" NavigateUrl='<%# FriendlyUrl.Href("~/Customers/Details", Item.CustomerId) %>' Text="Details" /> | 
					    <asp:HyperLink runat="server" NavigateUrl='<%# FriendlyUrl.Href("~/Customers/Edit", Item.CustomerId) %>' Text="Edit" /> | 
                        <asp:HyperLink runat="server" NavigateUrl='<%# FriendlyUrl.Href("~/Customers/Delete", Item.CustomerId) %>' Text="Delete" />
                    </td>
                </tr>
            </ItemTemplate>
        </asp:ListView>
    </div>
</asp:Content>

