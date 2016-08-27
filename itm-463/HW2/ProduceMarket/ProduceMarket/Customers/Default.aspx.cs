using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.Entity;
using ProduceMarket.Models;

namespace ProduceMarket.Customers
{
    public partial class Default : System.Web.UI.Page
    {
		protected ProduceMarket.Models.ApplicationDbContext _db = new ProduceMarket.Models.ApplicationDbContext();

        protected void Page_Load(object sender, EventArgs e)
        {
        }

        // Model binding method to get List of Customers entries
        // USAGE: <asp:ListView SelectMethod="GetData">
        public IQueryable<ProduceMarket.Models.Customers> GetData()
        {
            return _db.Customers;
        }
    }
}

