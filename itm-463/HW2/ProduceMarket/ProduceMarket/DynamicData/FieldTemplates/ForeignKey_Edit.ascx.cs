﻿using System;
using System.Collections.Specialized;
using System.ComponentModel.DataAnnotations;
using System.Web.DynamicData;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Linq;
using ProduceMarket.Models;

namespace ProduceMarket {
    public partial class ForeignKey_EditField : System.Web.DynamicData.FieldTemplateUserControl {

		protected ProduceMarket.Models.ApplicationDbContext _db = new ProduceMarket.Models.ApplicationDbContext();

		public string DataTypeName { get; set; }

		public string DataTextField { get; set; }

		public string DataValueField { get; set; }

        public IQueryable GetData()
        {
			var entityType = Type.GetType(this.DataTypeName);
            return _db.Set(entityType).AsQueryable();
        } 

        protected void Page_Init(object sender, EventArgs e) {
			Label1.Text = Column.DisplayName;
        }
		
		// show bootstrap has-error
		protected void Page_PreRender(object sender, EventArgs e)
        {
            // if validation error then apply bootstrap has-error CSS class
            var isValid = this.Page.ModelState.IsValidField(Column.Name);
            Div1.Attributes["class"] = isValid ? "form-group" : "form-group has-error";
        }


        protected override void ExtractValues(IOrderedDictionary dictionary)
        {
            // If it's an empty string, change it to null
            string value = DropDownList1.SelectedValue;
            if (String.IsNullOrEmpty(value))
            {
                value = null;
            }

            dictionary[Column.Name] = ConvertEditedValue(value);
        }


        public override Control DataControl {
            get {
                return DropDownList1;
            }
        }
    
    }
}
