namespace ProduceMarket.Migrations
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;
    using ProduceMarket.Models;

    internal sealed class Configuration : DbMigrationsConfiguration<ProduceMarket.Models.ApplicationDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
            AutomaticMigrationDataLossAllowed = true;
        }

        protected override void Seed(ProduceMarket.Models.ApplicationDbContext context)
        {
            context.Customers.AddOrUpdate(p => p.Name,
               new Customers
               {
                   CustomerId = 1,
                   Name = "Ivan Irons",
                   Address = "One Microsoft Way",
                   City = "Redmond",
                   State = "WA",
                   Zip = "10999",
                   Email = "ivani@wideworldimporters.com",
               },
               new Customers
               {
                   CustomerId = 2,
                   Name = "Brent Scholl",
                   Address = "5678 1st Ave W",
                   City = "Redmond",
                   State = "WA",
                   Zip = "10999",
                   Email = "brents@wideworldimporters.com",
               },
                new Customers
                {
                    CustomerId = 3,
                    Name = "Terrell Bettis",
                    Address = "9012 State St",
                    City = "Redmond",
                    State = "WA",
                    Zip = "10999",
                    Email = "terrellb@wideworldimporters.com",
                },
                new Customers
                {
                    CustomerId = 4,
                    Name = "Jo Cooper",
                    Address = "3456 Maple St",
                    City = "Redmond",
                    State = "WA",
                    Zip = "10999",
                    Email = "joc@wideworldimporters.com",
                },
                new Customers
                {
                    CustomerId = 5,
                    Name = "Ines Burnett",
                    Address = "7890 2nd Ave E",
                    City = "Redmond",
                    State = "WA",
                    Zip = "10999",
                    Email = "inesb@wideworldimporters.com",
                }
                );
        }
    }
}
