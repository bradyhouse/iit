using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(SimpleCommerce.Startup))]
namespace SimpleCommerce
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
