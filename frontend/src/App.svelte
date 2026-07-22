<script>
  import { onMount } from "svelte";

  // Initial Seed Data (matching Mockup asset)
  let initialThreads = [
    {
      id: "3b290dfc-859a-4c22-b25c-897c55dae002",
      contact: {
        id: "d09228fb-28c0-43ef-bc43-2313bfa7e462",
        name: "Marcus Sterling",
        telegramUsername: "marcus_sterling_exec",
        phoneNumber: "+15550199281",
        avatarUrl: "https://lh3.googleusercontent.com/aida-public/AB6AXuAlSZeJHNBqrtNxIbIiH4PG4khiblq1HeBOovztiyEB-HhRiQCBIpIa5nLzWTUBjsco_PWmBjn3EIAuD7oH_pome4AnuiC3bCcUPsEN3UNqe8mVTx5Xj_Lc-qzz4lnp6MTgW2K956DAWARORKdjj6NxflEeWCIjUJOP-dfrFnod8XsXB_C1O_nXEn6tMIZ-pvXzuS5005HUwpiivMIwZTMUVCPxo9YHKyAXM8_mfpvkDp68FZk0zO400hi8k1RtxTBx6wVnXnMo_4M",
        createdAt: "2026-07-22T18:00:00Z"
      },
      status: "human_attention",
      tag: "subscription_issue",
      unreadCount: 3,
      createdAt: "2026-07-22T18:05:00Z",
      updatedAt: "2026-07-22T18:13:21Z",
      lastMessage: {
        id: "f003e8cb-7e2a-466d-88b1-3e47e8f52219",
        threadId: "3b290dfc-859a-4c22-b25c-897c55dae002",
        senderType: "contact",
        senderName: "Marcus Sterling",
        text: "This is unacceptable, I need a refund immediately or I'm calling...",
        timestamp: "2026-07-22T18:13:21Z"
      }
    },
    {
      id: "d09228fb-28c0-43ef-bc43-2313bfa7e463",
      contact: {
        id: "d09228fb-28c0-43ef-bc43-2313bfa7e464",
        name: "Elena Rodriguez",
        telegramUsername: "elena_rod_creative",
        phoneNumber: "+15550123456",
        avatarUrl: "https://lh3.googleusercontent.com/aida-public/AB6AXuBvrZXxkMGrKliwlb2RRFPw9jvASQYNHQN_bmd0Nk9OrGwrSK1iNkHxsuSpGvN9x54M8yJoRce1EvEKEWGTxuMPso3NdtxQp891P8cjcmU3tGRghgaLt1JnuBc8wyk8S9HXBLertgVzrzGUTd1iY7nNvSyjFYWTAbdRGytq0l21uJ-WVliCuia9XnAcBP0BHQGM0orh0e0r-aLALGhFC0lrFsj3gizZQTTnMlVqgv6TdAewv9TtxHnzSAHp0jzrao6n_uphLVrkHms",
        createdAt: "2026-07-22T17:40:00Z"
      },
      status: "ai_assistant",
      tag: "sales_inquiry",
      unreadCount: 0,
      createdAt: "2026-07-22T17:45:00Z",
      updatedAt: "2026-07-22T18:00:00Z",
      lastMessage: {
        id: "f003e8cb-7e2a-466d-88b1-3e47e8f52220",
        threadId: "d09228fb-28c0-43ef-bc43-2313bfa7e463",
        senderType: "contact",
        senderName: "Elena Rodriguez",
        text: "Great, I will check those pricing tiers now. Thanks for the link!",
        timestamp: "2026-07-22T18:00:00Z"
      }
    },
    {
      id: "e10228fb-38c0-43ef-bc43-2313bfa7e464",
      contact: {
        id: "e10228fb-38c0-43ef-bc43-2313bfa7e465",
        name: "Dr. Julian Vance",
        telegramUsername: "dr_julian_v",
        phoneNumber: "+15550987654",
        avatarUrl: "https://lh3.googleusercontent.com/aida-public/AB6AXuAQjhmXnzK1ost6VsHvpAAjycDv_iVl83KnJVCYmUt9Ew7GRv005D2GEYCqb16ztrkZRIfCk_1PhX0fyo1_e_G9UiRvx9y1D0HdDxuhJTi0z59iOwduxzhCZrGgphEzH_RC3djsFr9pRekJuSEA6WeoVMiYibB6e4htJOXO01zfPIRKH96VXrxoRqPXD5Gswm9XIcVYve4VcH7b1qOTRN8SMJWak8KHVhb_dSb6NQN0dYyOGFsl42dA_EJpiPrtvOu_NwB8ebYvsWk",
        createdAt: "2026-07-22T17:10:00Z"
      },
      status: "pending",
      tag: "technical_support",
      unreadCount: 1,
      createdAt: "2026-07-22T17:15:00Z",
      updatedAt: "2026-07-22T17:33:00Z",
      lastMessage: {
        id: "f003e8cb-7e2a-466d-88b1-3e47e8f52221",
        threadId: "e10228fb-38c0-43ef-bc43-2313bfa7e464",
        senderType: "contact",
        senderName: "Dr. Julian Vance",
        text: "The API documentation seems to be missing the webhooks section...",
        timestamp: "2026-07-22T17:33:00Z"
      }
    },
    {
      id: "f10228fb-48c0-43ef-bc43-2313bfa7e465",
      contact: {
        id: "f10228fb-48c0-43ef-bc43-2313bfa7e466",
        name: "Sarah Jenkins",
        telegramUsername: "sarah_jenks_growth",
        phoneNumber: "+15550112233",
        avatarUrl: "https://lh3.googleusercontent.com/aida-public/AB6AXuDviU8eFsGf1XHUWiGpYYJ2o4oAAEBHOsoG2GwrcNNhGal0OoH2M5Pi0Uke_KsqFABG1Zg0FBhPnMdHsK40pAW5OCkIlF-b4C3O0klMTBaFlKbGqdCZY7_eLx0S3m5GVaPEt8peA-L9KPx9RXf4P8RIbJyaSsjD2e-yxF1GkbMz36F8H-nMUGav2jmvHbxtcVqomwZ_UJQZbroSkuA8jYYgOuqNX37ubxZDnBv1zKbRA74lzJsVy_RlO1XBNGv1VAvIVqdntgd_axw",
        createdAt: "2026-07-22T16:50:00Z"
      },
      status: "ai_assistant",
      tag: "new_lead",
      unreadCount: 0,
      createdAt: "2026-07-22T16:55:00Z",
      updatedAt: "2026-07-22T17:15:00Z",
      lastMessage: {
        id: "f003e8cb-7e2a-466d-88b1-3e47e8f52222",
        threadId: "f10228fb-48c0-43ef-bc43-2313bfa7e465",
        senderType: "ai_assistant",
        senderName: "AI Assistant",
        text: "Auto-reply: We have received your request and will get back to you shortly.",
        timestamp: "2026-07-22T17:15:00Z"
      }
    },
    {
      id: "010228fb-58c0-43ef-bc43-2313bfa7e466",
      contact: {
        id: "010228fb-58c0-43ef-bc43-2313bfa7e467",
        name: "Linda Wu",
        telegramUsername: "linda_wu_ops",
        phoneNumber: "+15550445566",
        avatarUrl: "https://lh3.googleusercontent.com/aida-public/AB6AXuB4bzO2ZAk00yLX-TIsLvoPdkzKVWxLxJpIeoKXaxPjKfrp1gYwTYb-uKAA8b0WbKSQHnOj_0QG6x4E4zM3lZUXdWj0MU0jmYui3-j0pMDnDU-_JHb0lMtrOA6ndye437HFbAAEzont1JdtvJKvSIZnr6-RQjdgML9UW45Uwvh1HO4WBWuFJmmFUERKOV92GjcObTCxnk1u-in_EMKYdjsVj9IwEG7ltvQBVvqkKKkEvvcpOqNcxx_egCLEOYvR1HmZcaX__1K49Tw",
        createdAt: "2026-07-22T18:10:00Z"
      },
      status: "human_attention",
      tag: "critical_incident",
      unreadCount: 0,
      createdAt: "2026-07-22T18:11:00Z",
      updatedAt: "2026-07-22T18:15:00Z",
      lastMessage: {
        id: "f003e8cb-7e2a-466d-88b1-3e47e8f52223",
        threadId: "010228fb-58c0-43ef-bc43-2313bfa7e466",
        senderType: "contact",
        senderName: "Linda Wu",
        text: "Escalating: My server is currently unresponsive. Help needed!",
        timestamp: "2026-07-22T18:15:00Z"
      }
    }
  ];

  // Initial Message Histories per Thread
  let initialMessages = {
    "3b290dfc-859a-4c22-b25c-897c55dae002": [
      { id: "m1", threadId: "3b290dfc-859a-4c22-b25c-897c55dae002", senderType: "contact", senderName: "Marcus Sterling", text: "Hello, I registered for premium last night.", timestamp: "2026-07-22T18:05:00Z" },
      { id: "m2", threadId: "3b290dfc-859a-4c22-b25c-897c55dae002", senderType: "ai_assistant", senderName: "AI Assistant", text: "Hi Marcus! Welcome to Premium. How can I assist you with your subscription today?", timestamp: "2026-07-22T18:06:00Z" },
      { id: "m3", threadId: "3b290dfc-859a-4c22-b25c-897c55dae002", senderType: "contact", senderName: "Marcus Sterling", text: "I got charged twice on my card! Check it now.", timestamp: "2026-07-22T18:10:00Z" },
      { id: "m4", threadId: "3b290dfc-859a-4c22-b25c-897c55dae002", senderType: "ai_assistant", senderName: "AI Assistant", text: "I apologize for the inconvenience. Let me check our billing logs.", timestamp: "2026-07-22T18:11:00Z" },
      { id: "m5", threadId: "3b290dfc-859a-4c22-b25c-897c55dae002", senderType: "contact", senderName: "Marcus Sterling", text: "This is unacceptable, I need a refund immediately or I'm calling...", timestamp: "2026-07-22T18:13:21Z" }
    ],
    "d09228fb-28c0-43ef-bc43-2313bfa7e463": [
      { id: "m6", threadId: "d09228fb-28c0-43ef-bc43-2313bfa7e463", senderType: "contact", senderName: "Elena Rodriguez", text: "Can you provide information about the Team pricing plan?", timestamp: "2026-07-22T17:45:00Z" },
      { id: "m7", threadId: "d09228fb-28c0-43ef-bc43-2313bfa7e463", senderType: "ai_assistant", senderName: "AI Assistant", text: "Sure! Our Team tier starts at $49/mo for up to 5 members with full features: https://leadgenbot.example.com/pricing", timestamp: "2026-07-22T17:46:00Z" },
      { id: "m8", threadId: "d09228fb-28c0-43ef-bc43-2313bfa7e463", senderType: "contact", senderName: "Elena Rodriguez", text: "Great, I will check those pricing tiers now. Thanks for the link!", timestamp: "2026-07-22T18:00:00Z" }
    ],
    "e10228fb-38c0-43ef-bc43-2313bfa7e464": [
      { id: "m9", threadId: "e10228fb-38c0-43ef-bc43-2313bfa7e464", senderType: "contact", senderName: "Dr. Julian Vance", text: "The API documentation seems to be missing the webhooks section...", timestamp: "2026-07-22T17:33:00Z" }
    ],
    "f10228fb-48c0-43ef-bc43-2313bfa7e465": [
      { id: "m10", threadId: "f10228fb-48c0-43ef-bc43-2313bfa7e465", senderType: "contact", senderName: "Sarah Jenkins", text: "Is there a free trial?", timestamp: "2026-07-22T17:14:00Z" },
      { id: "m11", threadId: "f10228fb-48c0-43ef-bc43-2313bfa7e465", senderType: "ai_assistant", senderName: "AI Assistant", text: "Auto-reply: We have received your request and will get back to you shortly.", timestamp: "2026-07-22T17:15:00Z" }
    ],
    "010228fb-58c0-43ef-bc43-2313bfa7e466": [
      { id: "m12", threadId: "010228fb-58c0-43ef-bc43-2313bfa7e466", senderType: "contact", senderName: "Linda Wu", text: "Escalating: My server is currently unresponsive. Help needed!", timestamp: "2026-07-22T18:15:00Z" }
    ]
  };

  // State vars
  let threads = [...initialThreads];
  let messages = { ...initialMessages };
  let selectedThreadId = "3b290dfc-859a-4c22-b25c-897c55dae002"; // default selected
  let currentFilter = "All"; // All, Unread, Needs human, AI Active
  let searchQuery = "";
  let typedMessage = "";
  let isSending = false;
  let isWsConnected = false;
  let serverModeActive = false; // Flag to indicate if real backend is serving requests

  // Computed state
  $: selectedThread = threads.find(t => t.id === selectedThreadId);
  $: currentMessages = messages[selectedThreadId] || [];

  $: filteredThreads = threads.filter(t => {
    // Search filter
    if (searchQuery.trim() !== "") {
      const q = searchQuery.toLowerCase();
      const matchName = t.contact.name.toLowerCase().includes(q);
      const matchMsg = t.lastMessage?.text?.toLowerCase().includes(q);
      const matchTag = t.tag.toLowerCase().includes(q);
      if (!matchName && !matchMsg && !matchTag) return false;
    }

    // Tab quick filters
    if (currentFilter === "Unread") {
      return t.unreadCount > 0;
    } else if (currentFilter === "Needs human") {
      return t.status === "human_attention";
    } else if (currentFilter === "AI Active") {
      return t.status === "ai_assistant";
    }
    return true; // All
  });

  // Mobile navigation state
  let mobileView = "list"; // list, chat

  // API Call helper
  async function fetchApi(url, options = {}) {
    try {
      const response = await fetch(url, options);
      if (response.ok) {
        serverModeActive = true;
        return await response.json();
      }
    } catch (e) {
      console.warn(`Falling back to simulated storage. API request to ${url} failed. Error:`, e);
    }
    return null;
  }

  // Initial Fetching
  async function loadInbox() {
    const data = await fetchApi("/api/v1/inbox/threads");
    if (data && data.threads) {
      threads = data.threads;
      // Fetch messages for active thread
      loadMessagesForThread(selectedThreadId);
    }
  }

  async function loadMessagesForThread(threadId) {
    const data = await fetchApi(`/api/v1/inbox/threads/${threadId}/messages`);
    if (data && data.messages) {
      messages[threadId] = data.messages;
    }
  }

  onMount(() => {
    loadInbox();
    initSimulatedWebSocket();
  });

  // Action: Select thread
  function selectThread(id) {
    selectedThreadId = id;
    loadMessagesForThread(id);
    mobileView = "chat";
    // Optimistic: Clear unread count when clicking
    const th = threads.find(t => t.id === id);
    if (th) {
      th.unreadCount = 0;
      threads = [...threads];
    }
  }

  // Action: Send message
  async function sendOperatorMessage() {
    if (!typedMessage.trim() || isSending) return;
    const msgText = typedMessage;
    typedMessage = "";
    isSending = true;

    // Create optimistic message
    const tempId = "opt-" + Date.now();
    const optimisticMsg = {
      id: tempId,
      threadId: selectedThreadId,
      senderType: "human_operator",
      senderName: "You",
      text: msgText,
      timestamp: new Date().toISOString()
    };

    // Append to list locally
    messages[selectedThreadId] = [...(messages[selectedThreadId] || []), optimisticMsg];

    // Update thread last message and automatically shift state to 'human_attention'
    const thIdx = threads.findIndex(t => t.id === selectedThreadId);
    if (thIdx !== -1) {
      threads[thIdx].lastMessage = optimisticMsg;
      threads[thIdx].status = "human_attention";
      threads[thIdx].updatedAt = new Date().toISOString();
      threads = [...threads];
    }

    // API Post
    const apiResult = await fetchApi(`/api/v1/inbox/threads/${selectedThreadId}/messages`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ text: msgText })
    });

    if (apiResult) {
      // Replace optimistic message with actual server message
      messages[selectedThreadId] = messages[selectedThreadId].map(m => m.id === tempId ? apiResult : m);
    }

    isSending = false;
  }

  // Action: Explicit Override Status
  async function overrideHandlerMode(targetMode) {
    if (!selectedThread) return;

    // Optimistic update
    const previous = selectedThread.status;
    selectedThread.status = targetMode;
    selectedThread.updatedAt = new Date().toISOString();
    threads = [...threads];

    // API Post
    const apiResult = await fetchApi(`/api/v1/inbox/threads/${selectedThreadId}/override`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ handlerMode: targetMode })
    });

    if (!apiResult) {
      console.log("Mock Mode: Status successfully overridden to", targetMode);
    } else {
      selectedThread.status = apiResult.currentStatus;
      threads = [...threads];
    }
  }

  // WebSocket / Realtime simulation for high-fidelity presentation and testing
  let typingStatus = { threadId: null, isTyping: false };

  function initSimulatedWebSocket() {
    isWsConnected = true;

    // Simulate real-time responses and events after some delay to showcase "Needs human" visual triggers
    setTimeout(() => {
      // Trigger a simulator: Dr. Julian Vance status changes to "Needs human" / human_attention
      const th = threads.find(t => t.contact.name === "Dr. Julian Vance");
      if (th) {
        typingStatus = { threadId: th.id, isTyping: true };

        setTimeout(() => {
          typingStatus = { threadId: null, isTyping: false };
          th.status = "human_attention";
          const newMsg = {
            id: "sim-" + Date.now(),
            threadId: th.id,
            senderType: "contact",
            senderName: "Dr. Julian Vance",
            text: "CRITICAL: I'm getting a 500 Server Error now. Please reply immediately!",
            timestamp: new Date().toISOString()
          };
          messages[th.id] = [...(messages[th.id] || []), newMsg];
          th.lastMessage = newMsg;
          th.unreadCount = (th.unreadCount || 0) + 1;
          th.updatedAt = new Date().toISOString();
          threads = [...threads];

          // Sound or visible flash simulation could be here
          console.log("WebSocket event: thread_status_changed and message_received", th);
        }, 3000);
      }
    }, 15000); // Trigger 15s after startup
  }

  // Helpers
  function getTagColor(tag) {
    switch (tag) {
      case "subscription_issue": return "bg-rose-100 text-rose-800 border-rose-200";
      case "sales_inquiry": return "bg-emerald-100 text-emerald-800 border-emerald-200";
      case "technical_support": return "bg-indigo-100 text-indigo-800 border-indigo-200";
      case "new_lead": return "bg-blue-100 text-blue-800 border-blue-200";
      case "critical_incident": return "bg-amber-100 text-amber-800 border-amber-200";
      default: return "bg-gray-100 text-gray-800 border-gray-200";
    }
  }

  // Formatting helper
  function formatTagName(tag) {
    return tag.split('_').map(w => w.charAt(0).toUpperCase() + w.slice(1)).join(' ');
  }

  function getFormattedTime(isoString) {
    if (!isoString) return "";
    const date = new Date(isoString);
    const diffMs = Date.now() - date.getTime();
    const diffMins = Math.floor(diffMs / 60000);

    if (diffMins < 1) return "Just now";
    if (diffMins < 60) return `${diffMins}m ago`;
    const hours = Math.floor(diffMins / 60);
    if (hours < 24) return `${hours}h ago`;
    return date.toLocaleDateString();
  }
</script>

<!-- Top Navigation/Header -->
<header class="glass-header bg-surface/90 border-b border-outline-variant/30 text-primary sticky top-0 z-40 flex justify-between items-center px-margin-mobile h-16 w-full">
  <div class="flex items-center gap-4">
    <button class="p-2 rounded-full hover:bg-surface-container-high transition-colors duration-200 focus-visible:outline focus-visible:outline-2 focus-visible:outline-primary" aria-label="Open main menu">
      <span class="material-symbols-outlined">menu</span>
    </button>
    <div class="flex items-center gap-2">
      <h1 class="font-headline-lg text-headline-lg font-bold text-primary tracking-tight">Unified Inbox</h1>
      <span class="inline-flex items-center px-2 py-0.5 rounded-full text-xs font-semibold bg-primary-container text-on-primary-container">
        Live Chat
      </span>
    </div>
  </div>

  <div class="flex items-center gap-3">
    <!-- Real-time Connection Badge -->
    <div class="flex items-center gap-1.5 px-3 py-1 bg-surface-container rounded-full border border-outline-variant/20">
      <span class="w-2.5 h-2.5 rounded-full {isWsConnected ? 'bg-emerald-500 animate-pulse' : 'bg-rose-500'}"></span>
      <span class="text-xs text-outline font-medium tracking-wide">
        {isWsConnected ? 'Live Connection' : 'Disconnected'}
      </span>
    </div>

    <!-- User Profile Dropdown -->
    <button class="w-9 h-9 rounded-full overflow-hidden border border-outline-variant focus-visible:outline focus-visible:outline-2 focus-visible:outline-primary" aria-label="Operator options">
      <img class="w-full h-full object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBvrZXxkMGrKliwlb2RRFPw9jvASQYNHQN_bmd0Nk9OrGwrSK1iNkHxsuSpGvN9x54M8yJoRce1EvEKEWGTxuMPso3NdtxQp891P8cjcmU3tGRghgaLt1JnuBc8wyk8S9HXBLertgVzrzGUTd1iY7nNvSyjFYWTAbdRGytq0l21uJ-WVliCuia9XnAcBP0BHQGM0orh0e0r-aLALGhFC0lrFsj3gizZQTTnMlVqgv6TdAewv9TtxHnzSAHp0jzrao6n_uphLVrkHms" alt="Operator avatar" />
    </button>
  </div>
</header>

<!-- Main Container with Responsive Two-Column Layout -->
<div class="max-w-7xl mx-auto px-4 py-6">
  <div class="grid grid-cols-1 md:grid-cols-12 gap-6 h-[calc(100vh-10rem)] min-h-[50rem]">

    <!-- Left Column: Chat Thread List (Full on Mobile if list active, hidden on Mobile if chat active) -->
    <aside class="col-span-12 md:col-span-5 lg:col-span-4 flex flex-col bg-surface-container-lowest rounded-xl border border-outline-variant/40 shadow-sm overflow-hidden {mobileView === 'chat' ? 'hidden md:flex' : 'flex'}" aria-label="Conversation threads">
      <!-- Search & Filters Header -->
      <div class="p-4 border-b border-outline-variant/30 space-y-3">
        <!-- Search Bar -->
        <div class="relative">
          <span class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none text-outline">
            <span class="material-symbols-outlined text-[20px]">search</span>
          </span>
          <input
            type="text"
            placeholder="Search name, message or tags..."
            bind:value={searchQuery}
            class="w-full pl-10 pr-4 py-2 bg-background border border-outline-variant/40 rounded-lg text-body-md placeholder-outline focus:outline-none focus:ring-2 focus:ring-primary focus:border-transparent transition-all duration-200"
            aria-label="Search threads"
          />
        </div>

        <!-- Quick Tabs -->
        <nav class="flex gap-1 overflow-x-auto pb-1" aria-label="Filter threads">
          {#each ["All", "Unread", "Needs human", "AI Active"] as filter}
            <button
              on:click={() => currentFilter = filter}
              class="flex-shrink-0 px-3 py-1.5 rounded-lg font-label-lg text-label-lg transition-all duration-200 {currentFilter === filter ? 'bg-primary text-on-primary shadow-sm font-semibold' : 'bg-surface hover:bg-surface-container-high text-on-surface-variant border border-outline-variant/25'}"
              aria-current={currentFilter === filter ? "page" : undefined}
            >
              {filter}
            </button>
          {/each}
        </nav>
      </div>

      <!-- Scrollable Thread List -->
      <div class="flex-1 overflow-y-auto p-3 space-y-3" role="tablist" aria-label="Inbox conversations" aria-busy="false">
        {#if filteredThreads.length === 0}
          <div class="text-center py-12 text-on-surface-variant/60">
            <span class="material-symbols-outlined text-4xl mb-2">forum</span>
            <p class="text-sm">No conversations found</p>
          </div>
        {:else}
          {#each filteredThreads as t (t.id)}
            <!-- Color-coded Thread Card -->
            <!-- CRITICAL REQ: When state changes to "Needs human" (human_attention), visual highlights are triggered for the operator -->
            <button
              on:click={() => selectThread(t.id)}
              role="tab"
              class="w-full text-left group relative flex items-start gap-4 p-4 rounded-xl transition-all duration-200 cursor-pointer focus-visible:outline focus-visible:outline-2 focus-visible:outline-primary
                {selectedThreadId === t.id ? 'bg-surface-container' : 'bg-surface-container-lowest hover:bg-surface-container-low'}
                {t.status === 'human_attention' ? 'border-2 border-secondary shadow-md ring-1 ring-secondary/20' : 'border border-outline-variant/30 shadow-sm'}"
              aria-selected={selectedThreadId === t.id}
            >
              <!-- Avatar Stack with Status Dot -->
              <div class="relative flex-shrink-0">
                <div class="w-12 h-12 rounded-full overflow-hidden border border-outline-variant bg-surface-container">
                  <img class="w-full h-full object-cover" src={t.contact.avatarUrl} alt="{t.contact.name} headshot" />
                </div>
                <!-- Status Dot Indicator: bg-error/Red for Needs human, primary/Blue for AI, yellow/Orange for pending -->
                <span class="absolute bottom-0 right-0 w-3.5 h-3.5 border-2 border-surface-container-lowest rounded-full
                  {t.status === 'human_attention' ? 'bg-error animate-pulse' : ''}
                  {t.status === 'ai_assistant' ? 'bg-primary' : ''}
                  {t.status === 'pending' ? 'bg-amber-500' : ''}"
                  title="Status: {t.status === 'human_attention' ? 'Needs human operator' : t.status}">
                </span>
              </div>

              <!-- Thread Information -->
              <div class="flex-1 min-w-0">
                <div class="flex justify-between items-center mb-1">
                  <h3 class="font-headline-sm text-headline-sm truncate text-on-surface group-hover:text-primary transition-colors duration-200">{t.contact.name}</h3>
                  <span class="font-timestamp text-timestamp text-outline whitespace-nowrap ml-2">
                    {getFormattedTime(t.updatedAt)}
                  </span>
                </div>

                <!-- Last Message Preview -->
                <p class="font-body-sm text-body-sm text-on-surface-variant line-clamp-1 mb-2 {t.unreadCount > 0 ? 'font-bold text-on-surface italic' : ''}">
                  {t.lastMessage ? t.lastMessage.text : 'No messages yet'}
                </p>

                <!-- Footer details: Status badge + Tag -->
                <div class="flex flex-wrap items-center gap-1.5">
                  <!-- Unified Color-coded Badge -->
                  {#if t.status === 'human_attention'}
                    <span class="inline-flex items-center px-2 py-0.5 rounded-md bg-secondary text-on-secondary font-label-lg text-label-lg shadow-sm">
                      <span class="material-symbols-outlined text-[14px] mr-1">warning</span>
                      Needs Human
                    </span>
                  {:else}
                    <span class="inline-flex items-center px-2 py-0.5 rounded-md bg-primary-container text-on-primary-container font-label-lg text-label-lg">
                      <span class="material-symbols-outlined text-[14px] mr-1">smart_toy</span>
                      AI Assistant
                    </span>
                  {/if}

                  {#if t.status === 'pending'}
                    <span class="inline-flex items-center px-2 py-0.5 rounded-md bg-amber-100 text-amber-800 border border-amber-200 font-label-lg text-label-lg">
                      <span class="material-symbols-outlined text-[14px] mr-1">pending</span>
                      Pending
                    </span>
                  {/if}

                  <!-- Unread Counter -->
                  {#if t.unreadCount > 0}
                    <span class="ml-auto inline-flex items-center justify-center px-2 py-0.5 rounded-full text-[10px] font-bold bg-error text-on-secondary shadow-sm">
                      {t.unreadCount} unread
                    </span>
                  {/if}

                  <!-- Context Tag -->
                  <span class="inline-flex items-center px-2 py-0.5 rounded-md border text-label-md font-label-md {getTagColor(t.tag)}">
                    {formatTagName(t.tag)}
                  </span>
                </div>
              </div>
            </button>
          {/each}
        {/if}
      </div>
    </aside>

    <!-- Right Column: Conversation detail pane (Full on Mobile if chat active, hidden on Mobile if list active) -->
    <main class="col-span-12 md:col-span-7 lg:col-span-8 flex flex-col bg-surface-container-lowest rounded-xl border border-outline-variant/40 shadow-sm overflow-hidden {mobileView === 'list' ? 'hidden md:flex' : 'flex'}" aria-label="Conversation window">
      {#if selectedThread}
        <!-- Conversation Header -->
        <header class="p-4 border-b border-outline-variant/30 flex justify-between items-center bg-surface-container-low/50">
          <div class="flex items-center gap-3">
            <!-- Back button for mobile -->
            <button
              on:click={() => mobileView = "list"}
              class="md:hidden p-2 rounded-full hover:bg-surface-container-high transition-colors text-primary focus-visible:outline focus-visible:outline-2 focus-visible:outline-primary"
              aria-label="Back to threads list"
            >
              <span class="material-symbols-outlined">arrow_back</span>
            </button>

            <!-- Selected User Headshot -->
            <div class="relative">
              <div class="w-10 h-10 rounded-full overflow-hidden border border-outline-variant">
                <img class="w-full h-full object-cover" src={selectedThread.contact.avatarUrl} alt={selectedThread.contact.name} />
              </div>
              <span class="absolute bottom-0 right-0 w-2.5 h-2.5 border-2 border-surface-container-lowest rounded-full
                {selectedThread.status === 'human_attention' ? 'bg-error' : ''}
                {selectedThread.status === 'ai_assistant' ? 'bg-primary' : ''}
                {selectedThread.status === 'pending' ? 'bg-amber-500' : ''}">
              </span>
            </div>

            <!-- Profile Info -->
            <div>
              <h2 class="font-headline-sm text-headline-sm text-on-surface flex items-center gap-2">
                {selectedThread.contact.name}
              </h2>
              <p class="font-body-sm text-body-sm text-outline flex items-center gap-1.5">
                <span class="font-semibold text-primary">@{selectedThread.contact.telegramUsername}</span>
                <span>•</span>
                <span>{selectedThread.contact.phoneNumber}</span>
              </p>
            </div>
          </div>

          <!-- Active AI Override Trigger Dropdown & Status Controls -->
          <div class="flex items-center gap-2">
            <span class="text-xs text-outline font-medium hidden lg:inline-block">AI Override Mode:</span>

            <div class="inline-flex rounded-lg border border-outline-variant/40 overflow-hidden shadow-sm bg-surface">
              <!-- AI Assistant Option -->
              <button
                on:click={() => overrideHandlerMode("ai_assistant")}
                class="px-3 py-1.5 text-xs font-semibold flex items-center gap-1 transition-all focus-visible:outline focus-visible:outline-2 focus-visible:outline-primary
                  {selectedThread.status === 'ai_assistant' ? 'bg-primary text-on-primary shadow-sm' : 'hover:bg-surface-container text-on-surface-variant'}"
                aria-pressed={selectedThread.status === 'ai_assistant'}
              >
                <span class="material-symbols-outlined text-[14px]">smart_toy</span>
                AI Active
              </button>

              <!-- Human Attention Option -->
              <button
                on:click={() => overrideHandlerMode("human_attention")}
                class="px-3 py-1.5 text-xs font-semibold flex items-center gap-1 transition-all focus-visible:outline focus-visible:outline-2 focus-visible:outline-primary
                  {selectedThread.status === 'human_attention' ? 'bg-secondary text-on-secondary shadow-sm' : 'hover:bg-surface-container text-on-surface-variant'}"
                aria-pressed={selectedThread.status === 'human_attention'}
              >
                <span class="material-symbols-outlined text-[14px]">warning</span>
                Manual Override
              </button>
            </div>
          </div>
        </header>

        <!-- Message List Timeline -->
        <div class="flex-1 overflow-y-auto p-4 space-y-4 bg-background/40" role="log" aria-live="polite">
          {#each currentMessages as msg (msg.id)}
            <article class="flex flex-col {msg.senderType === 'human_operator' ? 'items-end' : 'items-start'}">
              <!-- Sender Title Tag -->
              <span class="text-[10px] text-outline font-semibold mb-1 tracking-wider uppercase px-1">
                {msg.senderType === 'human_operator' ? 'You (Operator)' : msg.senderName}
              </span>

              <!-- Bubble Box -->
              <div class="max-w-[80%] rounded-xl px-4 py-2.5 shadow-sm border text-body-md relative
                {msg.senderType === 'human_operator'
                  ? 'bg-primary-container text-on-primary-container border-primary-container/20 rounded-tr-none'
                  : ''}
                {msg.senderType === 'contact'
                  ? 'bg-surface-container-lowest text-on-surface border-outline-variant/30 rounded-tl-none'
                  : ''}
                {msg.senderType === 'ai_assistant'
                  ? 'bg-surface-container text-on-surface-variant border-outline-variant/20 italic rounded-tl-none'
                  : ''}">

                <p class="whitespace-pre-wrap">{msg.text}</p>

                <!-- Tiny Timestamp inside the bubble footer -->
                <div class="text-[9px] text-right mt-1.5 opacity-70">
                  {new Date(msg.timestamp).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
                </div>
              </div>
            </article>
          {/each}

          <!-- Simulated typing indicator -->
          {#if typingStatus.threadId === selectedThreadId && typingStatus.isTyping}
            <div class="flex items-center gap-1 text-xs text-outline italic px-2">
              <span class="material-symbols-outlined text-[14px] animate-bounce">smart_toy</span>
              AI Assistant typing...
            </div>
          {/if}
        </div>

        <!-- Warning / Override Notice Message: Inform operator that AI is paused when manually handling -->
        {#if selectedThread.status === 'human_attention'}
          <div class="bg-secondary-fixed/50 text-on-secondary-fixed border-t border-b border-secondary/20 px-4 py-2.5 text-xs font-semibold flex items-center gap-2">
            <span class="material-symbols-outlined text-[16px] text-secondary">warning</span>
            <span>AI Assistant is paused for this chat. Manual operator is in control.</span>
            <button
              on:click={() => overrideHandlerMode("ai_assistant")}
              class="ml-auto underline hover:text-secondary focus-visible:outline focus-visible:outline-2 focus-visible:outline-primary"
            >
              Resume AI Response Automation
            </button>
          </div>
        {/if}

        <!-- Message Input Footer -->
        <footer class="p-4 border-t border-outline-variant/30 bg-surface-container-low/30">
          <form on:submit|preventDefault={sendOperatorMessage} class="flex gap-2">
            <input
              type="text"
              placeholder="Type message to {selectedThread.contact.name}... (Pressing Enter automatically pauses AI)"
              bind:value={typedMessage}
              class="flex-1 bg-surface-container-lowest border border-outline-variant/40 rounded-lg px-4 py-3 text-body-md focus:outline-none focus:ring-2 focus:ring-primary focus:border-transparent transition-all duration-200"
              aria-label="Type message"
            />
            <button
              type="submit"
              disabled={!typedMessage.trim() || isSending}
              class="px-5 py-3 rounded-lg bg-primary text-on-primary font-semibold flex items-center justify-center hover:scale-[1.02] active:scale-[0.98] focus-visible:outline focus-visible:outline-2 focus-visible:outline-primary transition-all duration-200 disabled:opacity-50 disabled:pointer-events-none"
              aria-label="Send message"
            >
              <span class="material-symbols-outlined text-[20px] mr-1">send</span>
              Send
            </button>
          </form>
        </footer>
      {:else}
        <!-- No selected thread view -->
        <div class="flex-1 flex flex-col items-center justify-center text-on-surface-variant/60">
          <span class="material-symbols-outlined text-6xl mb-3">forum</span>
          <p class="font-headline-md text-headline-md">Select a Thread</p>
          <p class="text-sm mt-1">Select a conversation from the sidebar to start live chatting.</p>
        </div>
      {/if}
    </main>

  </div>
</div>
