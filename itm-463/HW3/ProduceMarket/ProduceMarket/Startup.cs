using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(ProduceMarket.Startup))]
namespace ProduceMarket
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
