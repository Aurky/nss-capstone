const path = require('path');
const CopyPlugin = require("copy-webpack-plugin");
const Dotenv = require('dotenv-webpack');

// Get the name of the appropriate environment variable (`.env`) file for this build/run of the app
const dotenvFile = process.env.API_LOCATION ? `.env.${process.env.API_LOCATION}` : '.env';

module.exports = {
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: "static_assets", to: "../",
          globOptions: {
            ignore: ["**/.DS_Store"],
          },
        },
      ],
    }),
    new Dotenv({ path: dotenvFile }),
  ],
  optimization: {
    usedExports: true
  },
  entry: {
    index: path.resolve(__dirname, 'src', 'pages', 'index.js'),
    homePage: path.resolve(__dirname, 'src', 'pages', 'homePage.js'),
    createAd: path.resolve(__dirname, 'src', 'pages', 'createAd.js'),
    updateAd: path.resolve(__dirname, 'src', 'pages', 'updateAd'),
    userSetting: path.resolve(__dirname, 'src', 'pages', 'userSetting'),
    viewAd: path.resolve(__dirname, 'src', 'pages', 'viewAd'),
  },
  output: {
    path: path.resolve(__dirname, 'build', 'assets'),
    filename: '[name].js',
    publicPath: '/assets/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 8000,
    client: {
      // overlay shows a full-screen overlay in the browser when there are js compiler errors or warnings
      overlay: true,
    },
  }
}
