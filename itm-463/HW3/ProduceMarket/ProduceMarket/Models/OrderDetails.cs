using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace ProduceMarket.Models
{
    public class OrderDetails
    {
        [Key]
        public int OrderDetailId { get; set; }
        [Required]
        public int OrderId { get; set; }
        [ForeignKey("OrderId")]
        public virtual Orders Order { get; set; }
        [Required]
        public int Quantity { get; set; }
        [Required]
        public decimal? Price { get; set; }
        [EnumDataType(typeof(Vegetable)), Display(Name = "Produce")]
        public Vegetable Produce { get; set; }
        public decimal? Cost
        {
            get
            {
                if (this.Quantity > 0 && this.Cost > 0)
                {
                    return ((decimal)this.Quantity) * this.Price;
                }
                return (decimal)0;
            }
        }
    }
    public enum Vegetable
    {
        eggplant,
        broccoli,
        corn,
        apples,
        carrots,
        turnips,
        beets,
        tomatoes
    }
}