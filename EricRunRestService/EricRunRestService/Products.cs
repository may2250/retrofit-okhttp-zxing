using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;

namespace EricRunRestService
{
    [DataContract]
    public class Product
    {
        [DataMember]
        public int ProductId { get; set; }
        [DataMember]
        public string Name { get; set; }
        [DataMember]
        public string CategoryName { get; set; }
        [DataMember]
        public int Price { get; set; }
    }

    public partial class Products
    {
          public Product Instance 
          {
              get { return product; } 
          }

          private Product product = new Product()
          {
              ProductId = 1,
              Name = "Product 1",
              CategoryName = "Category 1",
              Price = 10
          };
      }
}

