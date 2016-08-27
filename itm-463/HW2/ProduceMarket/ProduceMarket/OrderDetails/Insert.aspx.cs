using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.Entity;
using ProduceMarket.Models;

namespace ProduceMarket.OrderDetails
{
    public partial class Insert : System.Web.UI.Page
    {
        string backURL;

		protected ProduceMarket.Models.ApplicationDbContext _db = new ProduceMarket.Models.ApplicationDbContext();

        protected void Page_Load(object sender, EventArgs e)
        {
            string requestedCustomerID = Request.QueryString["CustomerId"];
            string requestedOrderID = Request.QueryString["OrderId"];

            if (requestedCustomerID != null && requestedOrderID !=null)
            {
                this.backURL = "~/OrderDetails/Default.aspx?CustomerId=" + requestedCustomerID + "&OrderId=" + requestedOrderID;
            }

        }

        // This is the Insert method to insert the entered OrderDetails item
        // USAGE: <asp:FormView InsertMethod="InsertItem">
        public void InsertItem()
        {
            using (_db)
            {
                var item = new ProduceMarket.Models.OrderDetails();

                TryUpdateModel(item);

                if (ModelState.IsValid)
                {
                    // Save changes
                    _db.OrderDetails.Add(item);
                    _db.SaveChanges();

                    Response.Redirect(this.backURL);
                }
            }
        }

        protected void ItemCommand(object sender, FormViewCommandEventArgs e)
        {
            if (e.CommandName.Equals("Cancel", StringComparison.OrdinalIgnoreCase))
            {
                Response.Redirect(this.backURL);
            }
        }
    }
}
