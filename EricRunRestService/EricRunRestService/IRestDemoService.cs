using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;
using System.ServiceModel.Web;
using Newtonsoft.Json.Linq;
using System.IO;

namespace EricRunRestService
{
    [ServiceContract(Name = "RestDemoServices")]
    public interface IRestDemoServices
    {
        [OperationContract]
        [WebGet(UriTemplate = Routing.GetClientRoute, BodyStyle = WebMessageBodyStyle.Bare)]
        string GetClientNameById(string Id);

        [OperationContract]
        [WebGet(UriTemplate = Routing.GetProducts, BodyStyle = WebMessageBodyStyle.Bare, RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        Stream GetProducts();
    }


    public static class Routing
    {
        public const string GetClientRoute = "/Client/{id}";
        public const string GetProducts = "/Products";
    }
}
