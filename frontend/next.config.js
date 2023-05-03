/** @type {import('next').NextConfig} */
const nextConfig = {
  async rewrites() {
    return {
      fallback: [
        {
          source: '/api/:path*',
          destination: 'http://localhost:8080/api/:path*'
        }
      ]
    }
  },
  reactStrictMode: true,
  images: {
    unoptimized: true
  },
  swcMinify: true
}

module.exports = nextConfig
