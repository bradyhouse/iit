using System;
using System.Collections.Generic;
using System.Web;
using System.Configuration;

/// <summary>
/// Filter requests based on IP Address of the caller.
/// </summary>
public class IPFilterModule : IHttpModule
{
    const int FORBIDDEN = 403;

    public IPFilterModule() { }

    public String ModuleName
    {
        get { return "IPFilterModule"; }
    }

    public void Dispose() { }

    public void Init(HttpApplication context)
    {
        context.BeginRequest += (new EventHandler(this.Application_BeginRequest));
    }

    private void Application_BeginRequest(Object source, EventArgs e)
    {
        HttpApplication application = (HttpApplication)source;
        HttpContext context = application.Context;
        string ipAddress = context.Request.UserHostAddress;

        

        context.Response.Write("<br />");
        context.Response.Write("<hr>");

        if (!this.isValidAddress(ipAddress))
        {
            context.Response.StatusCode = FORBIDDEN;
        }
        else
        {
            string[] header = context.Request.Headers.AllKeys;

            string[] serverVars = context.Request.ServerVariables.AllKeys;

            context.Response.Write("User Host Address = " + ipAddress + "<br />");

            context.Response.Write("<H2>Request Headers</H2>");

            foreach (string key in header)
            {
                context.Response.Write(key + " = " + context.Request.Headers[key] + "<br />");
            }

            context.Response.Write("<H2>Server Variables</H2>");

            foreach (string variable in serverVars)
            {
                context.Response.Write(variable + " = " + context.Request.ServerVariables[variable] + "<br />");
            }
        }
    }

    
    private Boolean isValidAddress(string ipAddress) 
    {
        if (ipAddress == null) return true;

        string ipAddresses = ConfigurationManager.AppSettings["ForbiddenAddresses"];
        string[] addresses = ipAddresses.Split(',');
        foreach (string address in addresses)
        {
            if (address == ipAddress)
            {
                return false;
            }
        }
        return true;
    }


}