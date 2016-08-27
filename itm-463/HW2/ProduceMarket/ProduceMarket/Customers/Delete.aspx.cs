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

namespace ProduceMarket.Customers
{
    public partial class Delete : System.Web.UI.Page
    {
		protected ProduceMarket.Models.ApplicationDbContext _db = new ProduceMarket.Models.ApplicationDbContext();

        protected void Page_Load(object sender, EventArgs e)
        {
        }

        // This is the Delete methd to delete the selected Customers item
        // USAGE: <asp:FormView DeleteMethod="DeleteItem">
        public void DeleteItem(int CustomerId)
        {
            using (_db)
            {
                var item = _db.Customers.Find(CustomerId);

                if (item != null)
                {
                    _db.Customers.Remove(item);
                    _db.SaveChanges();
                }
            }
            Response.Redirect("~/OrderDetails/Default.aspx");
        }

        // This is the Select methd to selects a single Customers item with the id
        // USAGE: <asp:FormView SelectMethod="GetItem">
        public ProduceMarket.Models.Customers GetItem([FriendlyUrlSegmentsAttribute(0)]int? CustomerId)
        {
            if (CustomerId == null)
            {
                return null;
            }

            using (_db)
            {
	            return _db.Customers.Where(m => m.CustomerId == CustomerId).FirstOrDefault();
            }
        }

        protected void ItemCommand(object sender, FormViewCommandEventArgs e)
        {
            if (e.CommandName.Equals("Cancel", StringComparison.OrdinalIgnoreCase))
            {
                Response.Redirect("~/OrderDetails/Default.aspx");
            }
        }
    }
}

