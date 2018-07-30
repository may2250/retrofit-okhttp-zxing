using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel.Web;
using System.Text;
using EricRunRestService;

namespace EricRunHostService
{
    class Program
    {
        static void Main(string[] args)
        {
            RestDemoServices demoServices = new RestDemoServices();
            WebServiceHost _serviceHost = new WebServiceHost(demoServices, new Uri("http://localhost:8000/DemoService"));
            _serviceHost.Open();
            Console.ReadKey();
            _serviceHost.Close();
        }
    }
}
