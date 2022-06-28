﻿using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;

namespace SeatReservations.Api
{
    public class Program
    {
        public static void Main(string[] args)
        {
            CreateWebHostBuilder(args).Build().Run();
        }

        public static IWebHostBuilder CreateWebHostBuilder(string[] args)
        {
            return WebHost.CreateDefaultBuilder(args)
                
                .UseUrls("http://localhost:5000")
                .UseStartup<Startup>();
        }
    }
}