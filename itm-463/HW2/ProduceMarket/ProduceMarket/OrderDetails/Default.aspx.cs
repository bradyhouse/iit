using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.Entity;
using ProduceMarket.Models;
using System.Web.ModelBinding;


namespace ProduceMarket.OrderDetails
{
    public partial class Default : System.Web.UI.Page
    {
        int requestedOrderID;
        int requestedCustomerID;

		protected ProduceMarket.Models.ApplicationDbContext _db = new ProduceMarket.Models.ApplicationDbContext();

        protected void Page_Load(object sender, EventArgs e)
        {
            string requestedOrderID = Request.QueryString["OrderId"];
            string requestedCustomerID = Request.QueryString["CustomerId"];

            if (requestedOrderID != null && requestedCustomerID != null)
            {
                this.requestedCustomerID = int.Parse(requestedCustomerID);
                this.requestedOrderID = int.Parse(requestedOrderID);
            }
            else
            {
                this.requestedCustomerID = 1;
                this.requestedOrderID = 1;
            }

            if (this.GetData().Count() == 0)
            {
                OrderDetailsListView.Visible = false;
            }


            AddOrderDetailLink.NavigateUrl = "~/OrderDetails/Insert.aspx?CustomerId=" + this.requestedCustomerID.ToString() + "&OrderId=" + this.requestedOrderID.ToString();

        }

        // Model binding method to get List of OrderDetails entries
        // USAGE: <asp:ListView SelectMethod="GetData">
        public IQueryable<ProduceMarket.Models.OrderDetails> GetData()
        {
            return _db.OrderDetails.Include(m => m.Order)
                 .Where(e => e.OrderId == this.requestedOrderID);
        }
    }
}

