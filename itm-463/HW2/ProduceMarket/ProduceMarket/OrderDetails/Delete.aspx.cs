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
    public partial class Delete : System.Web.UI.Page
    {
		protected ProduceMarket.Models.ApplicationDbContext _db = new ProduceMarket.Models.ApplicationDbContext();

        protected void Page_Load(object sender, EventArgs e)
        {
        }

        // This is the Delete methd to delete the selected OrderDetails item
        // USAGE: <asp:FormView DeleteMethod="DeleteItem">
        public void DeleteItem(int OrderDetailId)
        {
            using (_db)
            {
                var item = _db.OrderDetails.Find(OrderDetailId);

                if (item != null)
                {
                    _db.OrderDetails.Remove(item);
                    _db.SaveChanges();
                }
            }
            Response.Redirect("../Default");
        }

        // This is the Select methd to selects a single OrderDetails item with the id
        // USAGE: <asp:FormView SelectMethod="GetItem">
        public ProduceMarket.Models.OrderDetails GetItem([FriendlyUrlSegmentsAttribute(0)]int? OrderDetailId)
        {
            if (OrderDetailId == null)
            {
                return null;
            }

            using (_db)
            {
	            return _db.OrderDetails.Where(m => m.OrderDetailId == OrderDetailId).Include(m => m.Order).FirstOrDefault();
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

