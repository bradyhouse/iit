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
namespace ProduceMarket.OrderDetails
{
    public partial class Edit : System.Web.UI.Page
    {
		protected ProduceMarket.Models.ApplicationDbContext _db = new ProduceMarket.Models.ApplicationDbContext();

        protected void Page_Load(object sender, EventArgs e)
        {
        }

        // This is the Update methd to update the selected OrderDetails item
        // USAGE: <asp:FormView UpdateMethod="UpdateItem">
        public void UpdateItem(int  OrderDetailId)
        {
            using (_db)
            {
                var item = _db.OrderDetails.Find(OrderDetailId);

                if (item == null)
                {
                    // The item wasn't found
                    ModelState.AddModelError("", String.Format("Item with id {0} was not found", OrderDetailId));
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

        // This is the Select method to selects a single OrderDetails item with the id
        // USAGE: <asp:FormView SelectMethod="GetItem">
        public ProduceMarket.Models.OrderDetails GetItem([FriendlyUrlSegmentsAttribute(0)]int? OrderDetailId)
        {
            if (OrderDetailId == null)
            {
                return null;
            }

            using (_db)
            {
                return _db.OrderDetails.Find(OrderDetailId);
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
