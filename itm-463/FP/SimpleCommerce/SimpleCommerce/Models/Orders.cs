using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;


namespace SimpleCommerce.Models
{
    public class Orders
    {
        [ScaffoldColumn(false)]
        [Key]
        public int OrderId { get; set; }
        [Required]
        public int CustomerId { get; set; }
        public virtual Customers Customer { get; set; }
        [Required]
        public int Quantity { get; set; }
        [Required]
        public Status Status { get; set; }
        [Required]
        public int ProductId { get; set; }
        public virtual Products Product { get; set; }
        public decimal? Cost
        {
            get
            {
                if (this.Quantity > 0 && this.Cost > 0)
                {
                    return ((decimal)this.Quantity) * this.Product.Price;
                }
                return (decimal)0;
            }
        }
    }

    public enum Status
    {
        Created,
        Processed,
        Shipped
    }
}