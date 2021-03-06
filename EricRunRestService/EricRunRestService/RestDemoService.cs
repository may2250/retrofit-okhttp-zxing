﻿using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Activation;
using System.Text;

namespace EricRunRestService
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single, ConcurrencyMode = ConcurrencyMode.Single, IncludeExceptionDetailInFaults = true)]
    [AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Allowed)]
    public class RestDemoServices : IRestDemoServices
    {
        public string GetClientNameById(string Id)
        {
            string ReturnString = "HaHa id is: " + Id;

            return ReturnString;
        }

        public Stream GetProducts()
        {
            dynamic obj = new JObject { { "ProductId", 10 }, { "Name", "xxxdfe" }, { "CategoryName", "jsdifadfi" }, { "Price", 52 } };

            return new MemoryStream(Encoding.UTF8.GetBytes(JsonConvert.SerializeObject(obj)));
        }
    }
}
