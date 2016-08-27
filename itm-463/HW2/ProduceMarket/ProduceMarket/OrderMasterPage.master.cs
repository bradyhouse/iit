using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.Entity;
using ProduceMarket.Models;
using System.Web.ModelBinding;


namespace ProduceMarket
{
    public partial class OrderMasterPage : System.Web.UI.MasterPage
    {
        private int selectedCustomerID;
        private int selectedOrderID;

        protected ProduceMarket.Models.ApplicationDbContext _db = new ProduceMarket.Models.ApplicationDbContext();

        protected void Page_Load(object sender, EventArgs e)
        {
            string requestedOrderID = Request.QueryString["OrderId"];
            string requestedCustomerID = Request.QueryString["CustomerId"];
            if (requestedCustomerID != null && requestedOrderID != null)
            {
                this.selectedCustomerID = int.Parse(requestedCustomerID);
                this.selectedOrderID = int.Parse(requestedOrderID);
            }
            else
            {
                this.selectedCustomerID = -1;
                this.selectedOrderID = 1;
            }

            if (this.GetData().Count() == 0)
            {
                OrderListView.Visible = false;
            }
            else
            {
                InsertOrderLink.NavigateUrl = "~/Orders/Insert.aspx?CustomerId=" + this.selectedCustomerID.ToString() + "&OrderId=" + this.selectedOrderID.ToString();
            }
        }
        // Model binding method to get List of Orders entries
        // USAGE: <asp:ListView SelectMethod="GetData">
        public IQueryable<ProduceMarket.Models.Orders> GetData()
        {
            return _db.Orders.Include(r => r.Customer)
                .Where(e => e.CustomerId == this.selectedCustomerID);
        }

        protected void OrderListView_SelectedIndexChanging(object sender, ListViewSelectEventArgs e)
        {
            ListViewDataItem item = OrderListView.Items[e.NewSelectedIndex];

            var customerIdLabel = (Label)item.FindControl("CustomerIdLabel");
            var orderIdLabel = (Label)item.FindControl("OrderIdLabel");

            if (customerIdLabel != null && orderIdLabel != null)
            {
                int customerId = int.Parse(customerIdLabel.Text);
                int orderId = int.Parse(orderIdLabel.Text);

                IQueryable<ProduceMarket.Models.Orders> orders = _db.Orders
                    .Where(c => c.CustomerId == customerId)
                    .Where(o => o.OrderId == orderId);

                if (orders != null && orders.Count() == 1)
                {
                    ProduceMarket.Models.Orders order = orders.First();
                    Response.Redirect("~/OrderDetails/Default.aspx?CustomerId=" + order.CustomerId.ToString() + "&OrderId=" + order.OrderId.ToString());
                }
                else
                {
                    Response.Redirect("~/OrderDetails/Default.aspx?CustomerId=" + customerIdLabel.Text);
                }
            }
          
        }
    }
}