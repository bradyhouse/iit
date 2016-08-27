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
    public partial class Edit : System.Web.UI.Page
    {
		protected ProduceMarket.Models.ApplicationDbContext _db = new ProduceMarket.Models.ApplicationDbContext();

        protected void Page_Load(object sender, EventArgs e)
        {
        }

        // This is the Update methd to update the selected Orders item
        // USAGE: <asp:FormView UpdateMethod="UpdateItem">
        public void UpdateItem(int  OrderId)
        {
            using (_db)
            {
                var item = _db.Orders.Find(OrderId);

                if (item == null)
                {
                    // The item wasn't found
                    ModelState.AddModelError("", String.Format("Item with id {0} was not found", OrderId));
                    return;
                }

                TryUpdateModel(item);

                if (ModelState.IsValid)
                {
                    // Save changes here
                    _db.SaveChanges();
                    Response.Redirect("../Default");
                }
            }
        }

        // This is the Select method to selects a single Orders item with the id
        // USAGE: <asp:FormView SelectMethod="GetItem">
        public ProduceMarket.Models.Orders GetItem([FriendlyUrlSegmentsAttribute(0)]int? OrderId)
        {
            if (OrderId == null)
            {
                return null;
            }

            using (_db)
            {
                return _db.Orders.Find(OrderId);
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
