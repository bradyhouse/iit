using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.Entity;
using ProduceMarket.Models;


namespace ProduceMarket
{
    public partial class CustomerMasterPage : System.Web.UI.MasterPage
    {
        int _selectedItemDisplayIndex;

        protected ProduceMarket.Models.ApplicationDbContext _db = new ProduceMarket.Models.ApplicationDbContext();

        protected void Page_Load(object sender, EventArgs e)
        {
            string requestedCustomerID = Request.QueryString["CustomerId"];

            if (requestedCustomerID != null)
            {
                this._selectedItemDisplayIndex = this.findListViewRowIndex(requestedCustomerID);
                CustomerListView.SelectedIndex = this._selectedItemDisplayIndex;
            }
        }

        protected void CustomerListView_SelectedIndexChanging(object sender, ListViewSelectEventArgs e)
        {
            ListViewDataItem item = CustomerListView.Items[e.NewSelectedIndex];

            var customerIdLabel = (Label)item.FindControl("CustomerIdLabel");

            if (customerIdLabel != null)
            {
                int customerId = int.Parse(customerIdLabel.Text);

                IQueryable<ProduceMarket.Models.Orders> orders = _db.Orders.Where(c => c.CustomerId == customerId);

                if (orders != null && orders.Count() >= 1)
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

        public IQueryable<ProduceMarket.Models.Customers> GetData()
        {
            return _db.Customers;
        }

        private int findListViewRowIndex(string CustomerId)
        {
            foreach(ListViewItem item in CustomerListView.Items) {

                Label CustomerIdLabel = (Label)item.FindControl("CustomerIdLabel");

                if (CustomerIdLabel != null && CustomerIdLabel.Text == CustomerId)
                {
                    return item.DataItemIndex;
                }
            }
            return -1;
        }

       

        private bool IsSelectDataItem(ListViewDataItem item)
        {
            return item.DisplayIndex == _selectedItemDisplayIndex; 
        }


    }
}