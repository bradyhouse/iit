using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.ModelBinding;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.Entity;
using Microsoft.AspNet.FriendlyUrls.ModelBinding;
using ProduceMarket.Models;

namespace ProduceMarket.Orders
{
    public partial class Details : System.Web.UI.Page
    {
		protected ProduceMarket.Models.ApplicationDbContext _db = new ProduceMarket.Models.ApplicationDbContext();

        protected void Page_Load(object sender, EventArgs e)
        {
        }

        // This is the Select methd to selects a single Orders item with the id
        // USAGE: <asp:FormView SelectMethod="GetItem">
        public ProduceMarket.Models.Orders GetItem([FriendlyUrlSegmentsAttribute(0)]int? OrderId)
        {
            if (OrderId == null)
            {
                return null;
            }

            using (_db)
            {
	            return _db.Orders.Where(m => m.OrderId == OrderId).Include(m => m.Customer).FirstOrDefault();
            }
        }

        protected void ItemCommand(object sender, FormViewCommandEventArgs e)
        {
            if (e.CommandName.Equals("Cancel", StringComparison.OrdinalIgnoreCase))
            {
                Response.Redirect("../Default");
            }
        }
    }
}

