/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{svelte,js,ts,jsx,tsx}",
  ],
  darkMode: "class",
  theme: {
    extend: {
      colors: {
        "on-surface": "#191c1e",
        "secondary": "#bc000a",
        "secondary-fixed-dim": "#ffb4aa",
        "primary-fixed": "#dae2ff",
        "secondary-container": "#e2241f",
        "tertiary-container": "#7d5200",
        "inverse-surface": "#2e3132",
        "on-secondary-fixed": "#410001",
        "on-tertiary-fixed-variant": "#624000",
        "tertiary-fixed": "#ffddb3",
        "background": "#f8f9fb",
        "outline-variant": "#c3c6d6",
        "inverse-primary": "#b2c5ff",
        "on-secondary": "#ffffff",
        "secondary-fixed": "#ffdad5",
        "surface": "#f8f9fb",
        "surface-container-highest": "#e1e2e4",
        "on-secondary-fixed-variant": "#930005",
        "primary-fixed-dim": "#b2c5ff",
        "on-primary-container": "#c4d2ff",
        "surface-variant": "#e1e2e4",
        "on-primary-fixed": "#001848",
        "tertiary": "#5e3c00",
        "on-error-container": "#93000a",
        "surface-dim": "#d9dadc",
        "inverse-on-surface": "#f0f1f3",
        "on-primary": "#ffffff",
        "surface-container-high": "#e7e8ea",
        "surface-bright": "#f8f9fb",
        "on-tertiary": "#ffffff",
        "on-background": "#191c1e",
        "on-tertiary-container": "#ffca81",
        "tertiary-fixed-dim": "#ffb950",
        "primary": "#003d9b",
        "outline": "#737685",
        "surface-container-lowest": "#ffffff",
        "error": "#ba1a1a",
        "surface-container-low": "#f3f4f6",
        "error-container": "#ffdad6",
        "on-tertiary-fixed": "#291800",
        "on-surface-variant": "#434654",
        "surface-tint": "#0c56d0",
        "on-primary-fixed-variant": "#0040a2",
        "on-secondary-container": "#fffbff",
        "primary-container": "#0052cc",
        "surface-container": "#edeef0",
        "on-error": "#ffffff"
      },
      borderRadius: {
        "DEFAULT": "0.25rem",
        "lg": "0.5rem",
        "xl": "0.75rem",
        "full": "9999px"
      },
      spacing: {
        "stack-sm": "0.5rem",       // 8px
        "base": "0.25rem",          // 4px
        "stack-lg": "1.5rem",       // 24px
        "gutter-mobile": "0.75rem", // 12px
        "stack-md": "1rem",         // 16px
        "margin-mobile": "1rem"     // 16px
      },
      fontFamily: {
        "headline-sm": ["Inter", "sans-serif"],
        "body-sm": ["Inter", "sans-serif"],
        "label-lg": ["Inter", "sans-serif"],
        "body-md": ["Inter", "sans-serif"],
        "timestamp": ["Inter", "sans-serif"],
        "headline-md": ["Inter", "sans-serif"],
        "headline-lg": ["Inter", "sans-serif"],
        "body-lg": ["Inter", "sans-serif"],
        "label-md": ["Inter", "sans-serif"]
      },
      fontSize: {
        "headline-sm": ["1rem", { "lineHeight": "1.5rem", "fontWeight": "600" }],
        "body-sm": ["0.8125rem", { "lineHeight": "1.125rem", "fontWeight": "400" }],
        "label-lg": ["0.75rem", { "lineHeight": "1rem", "letterSpacing": "0.02em", "fontWeight": "600" }],
        "body-md": ["0.875rem", { "lineHeight": "1.25rem", "fontWeight": "400" }],
        "timestamp": ["0.6875rem", { "lineHeight": "0.875rem", "fontWeight": "400" }],
        "headline-md": ["1.25rem", { "lineHeight": "1.75rem", "letterSpacing": "-0.01em", "fontWeight": "600" }],
        "headline-lg": ["1.5rem", { "lineHeight": "2rem", "letterSpacing": "-0.02em", "fontWeight": "700" }],
        "headline-lg-mobile": ["1.25rem", { "lineHeight": "1.75rem", "letterSpacing": "-0.02em", "fontWeight": "700" }],
        "body-lg": ["1rem", { "lineHeight": "1.5rem", "fontWeight": "400" }],
        "label-md": ["0.6875rem", { "lineHeight": "0.875rem", "fontWeight": "500" }]
      }
    },
  },
  plugins: [],
}
