using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;


namespace SimpleCommerce.Models
{
    public class Products
    {
        [ScaffoldColumn(false)]
        [Key]
        public int ProductId { get; set; }
        [Required]
        public string Name { get; set; }
        [Required]
        public string Description { get; set; }
        [Required]
        public decimal Price { get; set; }
        [Required]
        [EnumDataType(typeof(ProductType)), Display(Name = "Type")]
        public ProductType Type { get; set; }
    }

    public enum ProductType
    {
        eggplant,
        oranges,
        cauliflower,
        cucumber,
        okra,
        potatoes,
        broccoli,
        corn,
        apples,
        carrots,
        turnips,
        beets,
        tomatoes
    }
}